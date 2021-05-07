import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {CourseService} from '../../ServiceProvider/CourseService/course.service';
import {apiResponse} from '../../models/apiResponse';
import {Course} from '../../models/Course';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatDialog} from '@angular/material/dialog';
import {AddCourseComponent} from '../add-course/add-course.component';
import {UpdateCourseComponent} from '../update-course/update-course.component';

@Component({
  selector: 'app-view-courses',
  templateUrl: './view-courses.component.html',
  styleUrls: ['./view-courses.component.css'],
})
export class ViewCoursesComponent implements OnInit{

  courses = [];
  role: any;
  displayedColumns: string[];
  flag: boolean;
  // = ['courseId', 'courseName', 'courseDesc',
  //  'courseYear', 'courseLocation', 'coursePrerequisites', 'courseSkill', 'update', 'delete'];
  dataSource: MatTableDataSource<Course>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private courseService: CourseService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.role = sessionStorage.getItem('Designation');
    if (this.role === 'Admin' || this.role === 'Creator')
    {
      this.displayedColumns = ['courseId', 'courseName', 'courseDesc', 'courseYear', 'courseLocation', 'coursePrerequisites',
        'courseSkill', 'update', 'delete'];
      this.flag = true;
    }
    else {
      this.displayedColumns = ['courseId', 'courseName', 'courseDesc', 'courseYear', 'courseLocation',
        'coursePrerequisites', 'courseSkill'];
      this.flag = false;
    }

    this.courseService.getAllCourse().subscribe((response: apiResponse) => {
      this.courses = response.data;
      this.dataSource = new MatTableDataSource(this.courses);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }


  deleteCourse(id: number){
    console.log(id);
    if (confirm('You Sure want to delete the Course?')){
      this.courseService.deleteCourse(id).subscribe((response: apiResponse) => {
        if (response.result === 'Success')
        {
          this.ngOnInit();
        }
        else {
          alert('Error Occurred');
        }
      });
    }
  }

  updateCourse(id: number)
  {
    const dialogRef = this.dialog.open(UpdateCourseComponent, {
      width: '50%',
      height: '90%',
      data: id
    });

    dialogRef.afterClosed().subscribe( result => {
      this.ngOnInit();
    });
  }

  addCourse()
  {
    const dialogRef = this.dialog.open(AddCourseComponent, {
      width: '50%',
      height: '90%'
    });

    dialogRef.afterClosed().subscribe( result => {
      this.ngOnInit();
    });
  }
}

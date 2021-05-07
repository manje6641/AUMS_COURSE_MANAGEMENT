import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {User} from '../../models/User';
import {UserService} from '../../ServiceProvider/UserService/user.service';
import {MatDialog, MatDialogModule} from '@angular/material/dialog';
import {apiResponse} from '../../models/apiResponse';
import {AddUserComponent} from '../add-user/add-user.component';
import {UpdateUserComponent} from '../update-user/update-user.component';

@Component({
  selector: 'app-view-users',
  templateUrl: './view-users.component.html',
  styleUrls: ['./view-users.component.css']
})
export class ViewUsersComponent implements OnInit {

  role: any;
  users = [];
  flag: boolean;
  displayedColumns: string[];
  dataSource: MatTableDataSource<User>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  constructor(private userService: UserService, public dialog: MatDialog) { }

  ngOnInit(): void {
      this.role = sessionStorage.getItem('Designation');
      this.userService.getAllUser().subscribe((response: apiResponse) => {
          this.users = response.data;
          this.dataSource = new MatTableDataSource(this.users);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
        });
      if (this.role === 'Admin')
    {
      this.displayedColumns = ['UserId', 'UserName', 'UserEmail', 'UserDesignation', 'UserLocation', 'update', 'delete'];
      this.flag = true;
      }
    else  {
      this.displayedColumns = ['UserId', 'UserName', 'UserEmail', 'UserDesignation', 'UserLocation'];
      this.flag = false;
    }


  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  deleteUser(id: number){
    console.log(id);
    if (confirm('You Sure want to delete the Course?')){
      this.userService.deleteUser(id).subscribe((response: apiResponse) => {
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

  updateUser(id: number)
  {
    const dialogRef = this.dialog.open(UpdateUserComponent, {
      width: '50%',
      height: '70%',
      data: id
    });

    dialogRef.afterClosed().subscribe( result => {
      this.ngOnInit();
    });
  }

  addUser()
  {
    const dialogRef = this.dialog.open(AddUserComponent, {
      width: '50%',
      height: '70%'
    });

    dialogRef.afterClosed().subscribe( result => {
      this.ngOnInit();
    });
  }
}

import { Component, OnInit } from '@angular/core';
import {ChartDataSets, ChartOptions, ChartType} from 'chart.js';
import {Color, Label, monkeyPatchChartJsLegend, monkeyPatchChartJsTooltip, SingleDataSet} from 'ng2-charts';
import {TrendService} from '../../ServiceProvider/TrendService/trend.service';
import {apiResponse} from '../../models/apiResponse';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  // Pie 1
  public pieChartOptions0: ChartOptions = {
    responsive: true,
  };
  public pieChartLabels0: Label[] = [];
  public pieChartData0: SingleDataSet = [];
  public pieChartType0: ChartType = 'pie';
  public pieChartLegend0 = true;
  public pieChartPlugins0 = [];
  public pieChartColors0: Array < any > = [{
    backgroundColor: ['rgba(0,120,222,0.35)', 'rgba(174,108,255,0.35)', 'rgba(14,150,177,0.2)', 'rgba(255,0,86,0.35)'],
  }];

  // Pie 2
  public pieChartOptions1: ChartOptions = {
    responsive: true,
  };
  public pieChartLabels1: Label[] = [];
  public pieChartData1: SingleDataSet = [];
  public pieChartType1: ChartType = 'pie';
  public pieChartLegend1 = true;
  public pieChartPlugins1 = [];
  public pieChartColors1: Array < any > = [{
    backgroundColor: ['rgba(0,120,222,0.35)', 'rgba(174,108,255,0.35)', 'rgba(14,150,177,0.2)', 'rgba(255,0,86,0.35)'],
  }];

  // Line
  public lineChartData: ChartDataSets[] = [
    { data: [], label: 'Course Per Location' },
    { data: [], label: 'Student Per Location'}
  ];
  public lineChartLabels: Label[] = [] ;
  public lineChartOptions: ChartOptions  = {
    responsive: true,
  };
  public lineChartColors: Color[] = [
    {
      borderColor: 'black',
      backgroundColor: 'rgba(255,0,0,0.3)',
    },
  ];
  public lineChartLegend = true;
  public lineChartType = 'line';
  public lineChartPlugins = [];

  // Bar
  public barChartOptions: ChartOptions = {
    responsive: true,
  };
  public barChartLabels: Label[] = [];
  public barChartType: ChartType = 'bar';
  public barChartLegend = true;
  public barChartPlugins = [];

  public barChartData: ChartDataSets[] = [
    { data: [], label: 'Course Per Year' },
  ];

  constructor(private trendService: TrendService) {
    monkeyPatchChartJsTooltip();
    monkeyPatchChartJsLegend();
  }


  ngOnInit(): void {
    this.trendService.getTypeOfUser().subscribe((response0: apiResponse) => {
      for (let i = 0; i < response0.data.length; i++)
      {
        this.pieChartData0.push(response0.data[i].count);
        this.pieChartLabels0.push(response0.data[i].category);
      }
    });
    this.trendService.getCourseByLocation().subscribe((response1: apiResponse) => {
      for (let i = 0; i < response1.data.length; i++)
      {
        this.lineChartLabels.push(response1.data[i].category);
        this.lineChartData[0].data.push(response1.data[i].count);
      }
    });
    this.trendService.getStudentByLocation().subscribe((response2: apiResponse) => {
      for (let i = 0; i < response2.data.length; i++)
      {
        this.lineChartData[1].data.push(response2.data[i].count);
      }
    });
    this.trendService.getCourseByYear().subscribe((response3: apiResponse) => {
      for (let i = 0; i < response3.data.length; i++)
      {
        this.barChartLabels.push(response3.data[i].category);
        this.barChartData[0].data.push(response3.data[i].count);
      }
    });
    this.trendService.getFileByType().subscribe((response4: apiResponse) => {
      for (let i = 0; i < response4.data.length; i++)
      {
        this.pieChartData1.push(response4.data[i].count);
        this.pieChartLabels1.push(response4.data[i].category);
      }
    });
  }

}

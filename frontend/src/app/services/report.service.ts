import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Report } from '../models/report';
import { ReportView } from '../models/reportView';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  constructor(private http: HttpClient) { }

  // private sendReportUrl = 'http://localhost:8081/api/reports/submitreport';
  // private getReportsUrl = 'http://localhost:8081/api/reports/getreports/';
  // private getCheckedReportsUrl = 'http://localhost:8081/api/reports/getcheckedreports/';
  // private blockUserUrl = 'http://localhost:8081/api/users/blockuser/';
  // private unblockUserUrl = 'http://localhost:8081/api/users/unblockuser/';
  // private markAsCheckedUrl = 'http://localhost:8081/api/reports/markreportaschecked/';

  sendReport(report: Report) {
    return this.http.post('/api/reports/submitreport', report);
  }

  getReports(page: number) {
    //console.log("here");
    return this.http.get<ReportView[]>('/api/reports/getreports/' + page);
  }

  getCheckedReports(page: number) {
    return this.http.get<ReportView[]>('/api/reports/getcheckedreports/' + page);
  }

  blockUser(userId: number) {
    return this.http.get('/api/reports/blockuser/' + userId); 
  }

  unblockUser(userId: number) {
    return this.http.get('/api/reports/unblockuser/' + userId); 
  }

  markAsChecked(reportId: number) {
    return this.http.get<ReportView[]>('/api/reports/markreportaschecked/' + reportId); 
  }
}

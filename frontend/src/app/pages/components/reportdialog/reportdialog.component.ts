import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Report } from '../../../models/report';
import { ReportService } from '../../../services/report.service';

@Component({
  selector: 'app-reportdialog',
  templateUrl: './reportdialog.component.html',
  styleUrls: ['./reportdialog.component.css']
})
export class ReportdialogComponent {

  private reason: string;
  
  constructor(
    public dialogRef: MatDialogRef<ReportdialogComponent>,
    @Inject(MAT_DIALOG_DATA) public report: Report, private reportService: ReportService) {}

  onCancelClick(): void {
    this.dialogRef.close();
  }

  onSendClick(): void {
    if (this.reason !== undefined) {
      this.report.reason = this.reason;
      this.reportService.sendReport(this.report).subscribe();
      this.dialogRef.close();
    } else {
      window.confirm("You should write reason to submit report");
    }
  }

}

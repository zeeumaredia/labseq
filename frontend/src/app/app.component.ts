import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LabseqApiService } from './labseq-api.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  n = '';
  result = '';

  constructor(private api: LabseqApiService) {}

  submit() {
    this.result = '';
    this.api.getValue(this.n).subscribe({
      next: (r) => (this.result = r.value),
      error: (e) => (this.result = `Error: ${e?.error?.message ?? e?.message ?? 'Request failed'}`)
    });
  }
}

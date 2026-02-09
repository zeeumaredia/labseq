import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface LabseqResponse {
    n: number;
    value: string;
}

@Injectable({ providedIn: 'root' })
export class LabseqApiService {
    constructor(private http: HttpClient) {}

    getValue(n: number | string): Observable<LabseqResponse> {
        return this.http.get<LabseqResponse>(`/labseq/${n}`);
    }
}

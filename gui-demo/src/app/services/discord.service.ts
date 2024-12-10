import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {firstValueFrom, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DiscordService {
  constructor(private http: HttpClient) {
  }

  async getAccessToken(code: string) {
    const data = new URLSearchParams({
      client_id: '1144599605581979699',
      client_secret: 'xqt5MRuDRvh0j0QOAcFn2oIodxHyqEma',
      code: code,
      grant_type: 'authorization_code',
      redirect_uri: 'http://localhost:4200/callback'
    })
    const headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
    return firstValueFrom(this.http.post(`https://discord.com/api/oauth2/token`, data, {headers: headers}))
  }

  getJwt(accessToken: string): Promise<any> {
    let params = new HttpParams().set('access_token', accessToken)
    return firstValueFrom(this.http.post(`http://localhost:8080/auth/jwt`, {}, {params: params}));
    // return this.http.post<string>(`http://localhost:8080/auth-demo/auth/jwt/${accessToken}`, {});
  }

  getJwtAsObservable(accessToken: string): Observable<any> {
    let params = new HttpParams().set('access_token', accessToken)
    return this.http.post(`http://localhost:8080/auth/jwt`, {}, {params: params})
  }
}

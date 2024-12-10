import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DiscordAuthService {

  constructor() {
  }

  loginDiscord() {
    const clientId = '1144599605581979699'
    const redirectUri = 'http://localhost:4200/callback'
    window.location.href = `https://discord.com/api/oauth2/authorize?client_id=${clientId}&redirect_uri=${redirectUri}&response_type=code&scope=identify%20guilds.members.read`
  }
}

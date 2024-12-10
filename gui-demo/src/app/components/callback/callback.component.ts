import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DiscordService} from '../../services/discord.service';

@Component({
  selector: 'app-callback',
  imports: [],
  templateUrl: './callback.component.html',
  styleUrl: './callback.component.css'
})
export class CallbackComponent implements OnInit {
  constructor(private discordService: DiscordService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  async ngOnInit() {
    let code = ''
    this.route.queryParams.subscribe(params => code = params['code'])

    let accessToken = ''
    await this.discordService.getAccessToken(code).then((value: any) => {
      console.log(value)
      accessToken = value.access_token
    })
    let queryParams = {}
    await this.discordService.getJwt(accessToken).then(res => {
      localStorage.setItem('jwt', JSON.stringify(res.token))
      queryParams = {
        id: res.value.id,
        avatar: res.value.avatarHash
      }
    })
    await this.router.navigate(['/'], {queryParams: queryParams})
  }

}

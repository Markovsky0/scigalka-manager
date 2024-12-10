import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, RouterOutlet} from '@angular/router';
import {DiscordAuthService} from './services/discord-auth.service';
import {UserInfoComponent} from './components/user-info/user-info.component';

@Component({
  selector: 'app-root',
  imports: [
    UserInfoComponent,
    RouterOutlet
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  providers: []
})
export class AppComponent implements OnInit {
  title = 'gui-demo';
  userId: string = ''
  avatarHash: string = ''

  @ViewChild('userInfoRef', {static: true}) userInfoRef!: UserInfoComponent

  loginWithDiscord() {
    this.authService.loginDiscord();
  }

  constructor(private route: ActivatedRoute,
              private authService: DiscordAuthService) {
    this.route.queryParams.subscribe(params => {
      this.userId = params['id']
      this.avatarHash = params['avatar']
    })
  }

  ngOnInit() {
    this.userInfoRef.userId = this.userId
    this.userInfoRef.avatarHash = this.avatarHash
  }
}

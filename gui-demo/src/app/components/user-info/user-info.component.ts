import {AfterContentChecked, Component, Input} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-user-info',
  imports: [],
  templateUrl: './user-info.component.html',
  styleUrl: './user-info.component.css'
})
export class UserInfoComponent implements AfterContentChecked {
  @Input('userId')
  public userId: string = ''
  @Input('avatar')
  public avatarHash: string = ''
  private _avatarSource: string = ''
  get avatarSource(): string {
    return this._avatarSource
  }

  constructor(private router: Router) {}

  ngAfterContentChecked() {
    if (this.userId) {
      if (this.avatarHash) {
        const imageFormat: string = this.avatarHash.startsWith('a_') ? 'gif' : 'png'
        this._avatarSource = `https://cdn.discordapp.com/avatars/${this.userId}/${this.avatarHash}.${imageFormat}?size=64`
      } else {
        const defaultAvatarIndex = (BigInt(this.userId) >> 22n) % 6n
        this._avatarSource = `https://cdn.discordapp.com/embed/avatars/${defaultAvatarIndex}.png`
      }
    }
  }

  logout() {
    localStorage.clear();
    this._avatarSource = '';
    this.router.navigate(['/'])
  }
}

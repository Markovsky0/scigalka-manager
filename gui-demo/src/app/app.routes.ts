import {Routes} from '@angular/router';
import {CallbackComponent} from './components/callback/callback.component';
import {AppComponent} from './app.component';

export const routes: Routes = [
  // {
  //   path: '', component: AppComponent
  // },
  {
    path: 'callback', component: CallbackComponent, pathMatch: "full"
  }
];

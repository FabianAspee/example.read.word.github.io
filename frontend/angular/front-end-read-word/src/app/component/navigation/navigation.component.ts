import { Component } from "@angular/core"; 


@Component({
    selector:'navigation-component',
    templateUrl:'./navigation.template.html' 
})
export class NavigationComponent{
    public isMenuCollapsed = true; 
}
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { MathematicalOperationComponent } from "./mathematical_component/mathematical_operation.component";

const realTimeDatabaseRoutes: Routes = [  
    { path: 'mathematicaloperation', redirectTo: '/mathematicaloperationurl' }, 
    { path: 'mathematicaloperationurl', component: MathematicalOperationComponent}
  ];
  
  @NgModule({
    imports: [
      RouterModule.forChild(realTimeDatabaseRoutes)
    ],
    exports: [
      RouterModule
    ]
  })
  export class MathematicalOperationRoutingModule { }
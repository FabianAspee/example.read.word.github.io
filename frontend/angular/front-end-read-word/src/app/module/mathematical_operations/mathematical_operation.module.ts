import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { ButtonModule } from "primeng/button";
import { RippleModule } from "primeng/ripple";
import { ToastModule } from "primeng/toast";
import { SharedModule } from "../shared_module";
import { MathematicalOperationComponent } from "./mathematical_component/mathematical_operation.component";
import { MathematicalOperationRoutingModule } from "./mathematical_operation_routing.module";

@NgModule({
    imports: [
      ToastModule,
      ButtonModule,
      RippleModule,
      CommonModule,
      FormsModule,
      MathematicalOperationRoutingModule, 
      SharedModule
    ],
    declarations: [ 
        MathematicalOperationComponent
    ]
  })
  
  export class MathematicalOperationModule {} 
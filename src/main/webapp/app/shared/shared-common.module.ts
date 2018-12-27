import { NgModule } from '@angular/core';

import { SmaBackSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [SmaBackSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [SmaBackSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class SmaBackSharedCommonModule {}

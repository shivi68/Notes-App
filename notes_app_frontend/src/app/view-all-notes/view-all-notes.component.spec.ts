import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllNotesComponent } from './view-all-notes.component';

describe('ViewAllNotesComponent', () => {
  let component: ViewAllNotesComponent;
  let fixture: ComponentFixture<ViewAllNotesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ViewAllNotesComponent]
    });
    fixture = TestBed.createComponent(ViewAllNotesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

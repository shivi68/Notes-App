import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewNotesComponent } from './add-new-notes.component';

describe('AddNewNotesComponent', () => {
  let component: AddNewNotesComponent;
  let fixture: ComponentFixture<AddNewNotesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddNewNotesComponent]
    });
    fixture = TestBed.createComponent(AddNewNotesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StoryDetailPageComponent } from './story-detail-page.component';

describe('StoryDetailPageComponent', () => {
  let component: StoryDetailPageComponent;
  let fixture: ComponentFixture<StoryDetailPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StoryDetailPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StoryDetailPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

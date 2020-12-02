import { Component, OnInit } from '@angular/core';
import { DietManagementService } from '../services/diet-management.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Challenger } from '../models/challenger';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { MatRadioChange } from '@angular/material/radio';
import { HttpResponse } from '@angular/common/http';
import { User } from '../models/user';
import { UserService } from '../services/user.service';


@Component({
	selector: 'app-add-challenger',
	templateUrl: './add-challenger.component.html'
})
export class AddChallengerComponent implements OnInit {

	constructor(private dietService: DietManagementService, private userService: UserService) { }

	challenger: Challenger = new Challenger();
	user: User = new User();
	submitted = false;
	ngOnInit() {
		this.submitted = false;
	}

	public challengerDetails: Challenger;
	invalidLogin: boolean = false;

	challengersaveform = new FormGroup({
		fullName: new FormControl('', [Validators.required]),
		age: new FormControl('', [Validators.required]),
		gender: new FormControl('', [Validators.required]),
		mobile: new FormControl('', [Validators.required]),
		email: new FormControl('', [Validators.required, Validators.pattern('^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$')]),
		address: new FormControl('', [Validators.maxLength(150)]),
		city: new FormControl('', []),
		state: new FormControl('', [Validators.required]),
		country: new FormControl('', [Validators.required]),
		pincode: new FormControl('', [Validators.required]),
		height: new FormControl('', []),
		weight: new FormControl('', []),
		reason: new FormControl('', []),
		medicalCondition: new FormControl('', []),
		dietRestriction: new FormControl('', []),
		dietType: new FormControl('', []),
		pregnancyStatus: new FormControl('', []),
		referredCode: new FormControl('', [Validators.required])
	});

	saveChallenger(saveChallenger) {
		console.log('inside saveChallenger(saveChallenger)');

		this.challenger = new Challenger();
		this.challenger.fullName = this.challengersaveform.get('fullName').value;
		this.challenger.age = this.challengersaveform.get('age').value;
		this.challenger.gender = this.challengersaveform.get('gender').value;;
		this.challenger.mobile = this.challengersaveform.get('mobile').value;
		this.challenger.email = this.challengersaveform.get('email').value;
		this.challenger.address = this.challengersaveform.get('address').value;
		this.challenger.city = this.challengersaveform.get('city').value;
		this.challenger.state = this.challengersaveform.get('state').value;
		this.challenger.country = this.challengersaveform.get('country').value;
		this.challenger.pincode = this.challengersaveform.get('pincode').value;
		this.challenger.height = this.challengersaveform.get('height').value;
		this.challenger.weight = this.challengersaveform.get('weight').value;
		this.challenger.reason = this.challengersaveform.get('reason').value;
		this.challenger.medicalCondition = this.challengersaveform.get('medicalCondition').value;
		this.challenger.dietRestriction = this.challengersaveform.get('dietRestriction').value;
		this.challenger.dietType = this.challengersaveform.get('dietType').value;
		this.challenger.pregnancyStatus = this.challengersaveform.get('pregnancyStatus').value;
		this.challenger.referredCode = this.challengersaveform.get('referredCode').value;
		this.submitted = true;
		this.saveUserChallengerData();
	}

	saveUserChallengerData() {
		this.user.fullName = this.challenger.fullName;
		this.user.email = this.challenger.email;
		this.user.password = "password@123";
		this.user.mobileNo = this.challenger.mobile;
		this.user.userType = "CHALLENGER";
		this.dietService.saveUser(this.user)
			.pipe(map((res: Challenger) => {
				if (res != null) {
					console.log(res.fullName + " user saved uccessfully!")
				}
			}))
			.pipe(catchError((error: any) => {
				if (error.status < 400 || error.status === 500) {
					return Observable.throw(new Error(error.status));
				}
			}))
			.subscribe(res => console.log(res), error => console.log(error));

		this.dietService.saveChallenger(this.challenger)
			.pipe(map((res: Challenger) => {
				if (res != null) {
					console.log(res.fullName + " challenger saved uccessfully!")
				}
			}))
			.pipe(catchError((error: any) => {
				if (error.status < 400 || error.status === 500) {
					return Observable.throw(new Error(error.status));
				}
			}))
			.subscribe(res => console.log(res), error => console.log(error));

		this.challenger = new Challenger();
	}

	addChallengerForm() {
		this.submitted = false;
		this.challengersaveform.reset();
		this.challengersaveform.markAsUntouched();
		this.challengersaveform.markAsPristine();
		this.challengersaveform.clearValidators();
		this.challengersaveform.updateValueAndValidity();
	}

}

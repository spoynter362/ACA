/*
 * Angular module and controller for the create customer page and features.
 * 
 */
var createApp = angular.module('customerCreateApp', []);

createApp.controller('customerCreateCtrl', function($scope, $http) {
	
	$scope.createCustomer = function() {	
		
		if ($scope.newCustomerName == undefined) {
			$scope.nameMessage = "name is required";
		} else if ($scope.newCustomerName.length < 4) {
			$scope.nameMessage = "name must be at least 4 characters";
		} else {
			$scope.nameMessage = "";
		}
		
		if ($scope.newCustomerCity == undefined) {
			$scope.cityMessage = "city is required";
		} else if ($scope.newCustomerCity.length < 4) {
			$scope.cityMessage = "city must be at least 4 characters";
		} else {
			$scope.cityMessage = "";
		}
		
		if ($scope.newCustomerEmail == undefined || $scope.newCustomerEmail == "") {
			$scope.emailMessage = "";
			$scope.newCustomerEmail = "";
		} else if ($scope.newCustomerEmail.length < 6) {
			$scope.emailMessage = "email must be at least 6 characters";
		} else {
			$scope.emailMessage = "";
		}			
		
		if ($scope.nameMessage == "" && $scope.cityMessage == "" && $scope.emailMessage == "") 
		{
						
			alert("time to create a customer " +
					", name: " + $scope.newCustomerName +
					", city: " + $scope.newCustomerCity + 					
					", email: " + $scope.newCustomerEmail);					
			
			var newCustomer = { name : $scope.newCustomerName,
								city : $scope.newCustomerCity,
								email: $scope.newCustomerEmail };
			
			$http.post("/resttest/webapi/customers", newCustomer)
			.then(
					function success(response) {						
						if (response.data == 1) {						
							alert("rows inserted: " + response.data + ", status: " + response.status);
							$scope.createStatus = "success. press 'Clear' to enter new customer";							
						} else {
							alert("error, return status: " + response.status);
							$scope.createStatus = "error. press 'Clear' to try again";		
						}
					},
					function error(response) {
						alert("error, return status: " + response.status);
						$scope.createStatus = "error. press 'Clear' to try again";						
					}				
			);
			
			$scope.isCreateCustomerDisabled = true;
			$scope.lock = true;
			
		} else {
			$scope.createStatus = "please fix indicated errors";
		}				
			
	};
	
	$scope.clearCreate = function() {	
		
		//clear success or error message
		$scope.createStatus = "";
		
		//clear the input elements
		$scope.newCustomerName = "";
		$scope.newCustomerCity = "";
		$scope.newCustomerEmail = "";
				
		//clear the messages		
		$scope.nameMessage = "";
		$scope.cityMessage = "";
		$scope.emailMessage = "";
		
		//unlock input
		$scope.lock = false;
		$scope.isCreateCustomerDisabled = false;
	}
});	
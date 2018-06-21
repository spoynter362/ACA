/**
 creates a new angular module, and new module is tied to variable 'custApp'.
*/
var custApp = angular.module("customerApp",[]);

/**  
 controller is a JavaScript object created by a standard JavaScript object constructor.
 creates the controller name 'customerCtrl' and passes in the $scope object.
 you can add properties to the $scope object, the view can access them.
*/
custApp.controller('customerCtrl', function($scope, $http) {	
	
	$scope.companyName = 'company name: Lazenby Consulting';	
	$scope.navName = 'Customer Management App - GDC LLC';
	$scope.hideCustomerSearch = true;
	$scope.hideCustomerEditDelete = true;
	
	$scope.resource = '/resttest/webapi/customers/';
	
	$scope.customerSearch = function() {
		$scope.hideCustomerSearch = false;
		$scope.hideCustomerInfo = true;	
		$scope.hideCustomerSearchResults = true;
		$scope.hideCustomerEditDelete = true;	
	};
	
	$scope.updateCustomer = function(selectedCustomer){
		$scope.hideCustomerSearch = true;
		$scope.hideCustomerInfo = true;
		$scope.hideCustomerSearchResults = true;
		$scope.hideCustomerEditDelete = false;
		
		$scope.customer = selectedCustomer;
	};
	
	$scope.deleteCustomer = function(customerId) {		
		alert("time to delete, customer json: "+customerId);
		
		$http.delete($scope.resource + customerId)
		.then(
				function success(response) {
					$scope.rowCount = response.data;
					alert("customer deleted: " + $scope.rowCount + ", status: " + response.status);
				},
				function error(response){
					alert("error, return status: " + response.status)
				}
		);	
	};
	
	$scope.putCustomer = function() {		
		$scope.jsonObject = angular.toJson($scope.customer, false);
		alert("time to update, customer json: "+$scope.jsonObject);
		
		$http.put($scope.resource, $scope.jsonObject)
		.then(
				function success(response) {
					$scope.updateCustomer = response.data;
					alert("customer updated: " + $scope.updateCustomer.id + ", status: " + response.status);
				}
		);	
	};
	
	$scope.getCustomers = function() {
		$scope.hideCustomerSearchResults = false;
		
		$http.get($scope.resource).then(function(response){
			$scope.myCustomers = response.data;
		});
	}	
	
}); //controller method ends here
angular.module('passwordmeter').controller('PasswordMeterController', function($scope, $http) {
	
	$scope.score = {
		strength : 0,
		complexity : "Too Short"
	};
	
	$scope.password = "";
	
	$scope.keyUp = function() {
		if($scope.password == "") {
			$scope.score = {
				strength : 0,
				complexity : "Too Short"
			};
			return;
		}
		
		$http.get("/calculateScore/" + $scope.password)
		.success(function(data) {
			$scope.score = data;
		})
		.catch(function(error) {
			console.log(error);
		});
	}
});
protected void processReport(List<Employee> employees)
	{
		// generate some data from employees
		numberOfEmployees = employees.size();

		if (numberOfEmployees < 1) {
			throw new RuntimeException("Could not generate report - employees < 1");
		}

		averageAgeOfEmployees = 0;

		for (Employee employee : employees) {

			averageAgeOfEmployees += employee.getBirthday().getTime();
		}

		averageAgeOfEmployees /= numberOfEmployees;

		// @todo not proper way to calculate the age in years - but for demo purpose well enough
		averageAgeOfEmployees = ((new Date()).getTime() - averageAgeOfEmployees) / 1000.0 / 60.0 / 60.0 / 24.0 / 365.0;

		log.debug("Report", numberOfEmployees, averageAgeOfEmployees);
	}
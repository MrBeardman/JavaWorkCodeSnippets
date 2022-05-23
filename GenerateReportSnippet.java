public class DailySupportReportTask extends AbstractReportTask
{

	

	protected EmailService email;
	protected EmailTemplate emailTemplate;
	protected String receivers;
	protected List<String> employees;
	protected List<Integer> projects;

	
	protected DBObjectListStatement fakturierbarkeitQuery;
	protected DBObjectListStatement fakturierbareStundenQuery;
	

	protected String hoursBillableSum;
	protected String hoursNonBillableSum;
	
	protected String generateTableNF;
	protected String generateTableF;

	@Override
	protected void runTask() throws Exception
	{
		
		//Add date
		Date reportDate = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		reportDate = format.parse("2022-05-09");


		//Summe fakturierbarer und nicht fakturierbarer Stunden

		//list for adding hours to the first table
		List<String> hoursSumme = new ArrayList<String>();
		
		//add the start of the table to variables
		 generateTableNF ="<tr>\n" +"<td>nicht fakturierbar</td>";
		 generateTableF ="<tr>\n" +"<td>fakturierbar</td>";
		//for each name in List "names"  and 
		for (String employee:employees) {
			//run sql fakturierbarkeitQuery
			List<DailySupportReportString> DailySupportReportList = fakturierbarkeitQuery.execute(employee, reportDate, employee, reportDate);
				//get me fakturable and nonfakturable hours and save those to "hoursSumme"
				hoursSumme.add(DailySupportReportList.get(0).GetDailySupportReportString());
				hoursSumme.add(DailySupportReportList.get(1).GetDailySupportReportString());
		}
		
			//variables for hours billable and non billable sums
				double hoursNonBillableDoubleSum = 0;
				double hoursBillableDoubleSum = 0;
			//generateTable for frontedn use
			for (int i = 0; i < hoursSumme.size(); i += 2){
			generateTableNF += "<td>"+hoursSumme.get(i)+"</td>";
			
			String str = hoursSumme.get(i).toString();
			str = str.replace("h", "");
			hoursNonBillableDoubleSum = hoursNonBillableDoubleSum + Double.valueOf(str);
			}
			
			//making them back into string so i can display them as such in the html
			hoursNonBillableSum = hoursNonBillableDoubleSum +"h";
			
			
			for (int j = 1; j < hoursSumme.size(); j+=2){
			generateTableF += "<td>"+hoursSumme.get(j)+"</td>";


			String str = hoursSumme.get(j).toString();
				str = str.replace("h", "");
				hoursBillableDoubleSum = hoursBillableDoubleSum + Double.valueOf(str);
				hoursBillableDoubleSum = round(hoursBillableDoubleSum,2);
			}
			
			generateTableNF +="<td>$hoursNonBillableSum</td></tr>";
		//making them back into string so i can display them as such in the html
		hoursBillableSum = hoursBillableDoubleSum +"h";
		
		
		/* **************************************************** */
		//FAKTURIERBARE STUNDEN
		
		List<String> hoursFakturierbare = new ArrayList<String>();

		//nested for loops, first loop goes trough names, and 2nd goes trough projects, so this will get all projects 
		//for one person and then goes to another person
		for(String employee : employees){
		for(int project: projects){
			List<DailySupportReportString> DailySupportReportList = fakturierbareStundenQuery.execute(employee,project);
			hoursFakturierbare.add(DailySupportReportList.get(0).GetDailySupportReportString());
		}
		}
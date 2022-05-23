protected void runTask() throws Exception
	{
		log.info("Generating Report");

		// Create data for the table
		DefaultDynamicTableData data = new DefaultDynamicTableData(3, 4, true);

		// Fill table header
		for (int c = 0; c < data.getColumns(); ++c) {
			data.setHeader(c, "Header " + c);
		}

		// Fill table body
		for (int r = 0; r < data.getRows(); ++r) {
			for (int c = 0; c < data.getColumns(); ++c) {
				data.setCell(r, c, "Cell " + r + ":" + c);
			}
		}

		// Set data into table
		table.setData(data);

		// Render the table into a html string
		String tableHtml = table.toHtml();

		// Prepare report email
		EmailTemplateContext context = new EmailTemplateContext();
		context.setValue("table", tableHtml);

		// Send email
		email.sendEmail(receivers, emailTemplate, context);

		log.info("Done Generating Report");
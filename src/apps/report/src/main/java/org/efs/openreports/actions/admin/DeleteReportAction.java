/*
 * Copyright (C) 2003 Erik Swenson - erik@oreports.com
 * 
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 * 
 * You should have reserved a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place - Suite 330, Boston, MA 02111-1307, USA.
 *  
 */

package org.efs.openreports.actions.admin;

import org.efs.openreports.objects.Report;
import org.efs.openreports.providers.ReportProvider;

public class DeleteReportAction	extends DeleteAction
{
	private static final long serialVersionUID = -3773996844154734474L;
	
	private ReportProvider reportProvider;

	public String execute()
	{
		try
		{
			Report report = reportProvider.getReport(new Integer(id));

			name = report.getName();
			description = report.getDescription();

			if (!submitDelete && !submitCancel)
			{
				return INPUT;
			}

			if (submitDelete)
			{
				reportProvider.deleteReport(report);
			}
		}
		catch (Exception e)
		{			
			addActionError(getText(e.getMessage()));
			return INPUT;
		}

		return SUCCESS;
	}

	public void setReportProvider(ReportProvider reportProvider)
	{
		this.reportProvider = reportProvider;
	}

}
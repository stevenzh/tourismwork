<?xml version="1.0" encoding="UTF-8"?>
<!-- $Id: GACustomerListPrint.xsl,v 1.1 2009/03/01 16:24:15 zhangst Exp $ -->
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo">
	<xsl:output method="xml" version="1.0" omit-xml-declaration="no"
		indent="yes" />
	<xsl:param name="versionParam" select="'1.0'" />

	<!-- ========================= -->
	<!--          港澳游名单表        -->
	<!-- ========================= -->
	<xsl:template match="tour">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format"
			language="zh">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="simpleA4"
					page-height="29.7cm" page-width="21cm" margin-top="23mm"
					margin-bottom="5mm" margin-left="21mm" margin-right="21mm"><!-- margin-top="16mm" -->
					<fo:region-body margin-top="3.6cm"
						margin-bottom="9.0cm" /><!-- top 3.9cm -->
					<fo:region-before extent="2.8cm" />
					<fo:region-after extent="8.6cm "/>
				</fo:simple-page-master>
			</fo:layout-master-set>

			<fo:page-sequence master-reference="simpleA4">

				<!-- Header -->
				<fo:static-content flow-name="xsl-region-before">
					<fo:block font-size="10pt">
						<xsl:call-template name="header" />
					</fo:block>
				</fo:static-content>

				<!-- Footer -->
				<fo:static-content flow-name="xsl-region-after">
					<fo:block font-size="10pt">
						<xsl:call-template name="footer" />
					</fo:block>
				</fo:static-content>

				<!-- Body -->
				<fo:flow flow-name="xsl-region-body">
					<fo:block font-size="11pt">
						<fo:table table-layout="fixed" width="100%">
							<fo:table-column column-width="14mm" />
							<fo:table-column column-width="27mm" />
							<fo:table-column column-width="37mm" />
							<fo:table-column column-width="12mm" />
							<fo:table-column column-width="21.5mm" />
							<fo:table-column column-width="17.5mm" />
							<fo:table-column column-width="30mm" />
							<fo:table-column column-width="14mm" />

							<fo:table-body>
								<xsl:apply-templates select="customer" />
							</fo:table-body>
						</fo:table>
					</fo:block>
				</fo:flow>

			</fo:page-sequence>
		</fo:root>
	</xsl:template>

	<!-- Customer -->
	<xsl:template match="customer">
      <fo:table-row height="6.85mm"><!-- 7.2mm -->
			<fo:table-cell>
				<fo:block font-family="mysimsun"></fo:block>
			</fo:table-cell>
			<fo:table-cell>
				<fo:block font-family="mysimsun" text-align="center">
					<xsl:value-of select="printname" />
				</fo:block>
			</fo:table-cell>
			<fo:table-cell>
				<fo:block font-family="mysimsun" text-align="center">
					<xsl:value-of select="printpinyin" />
				</fo:block>
			</fo:table-cell>
			<fo:table-cell>
				<fo:block font-family="mysimsun" text-align="center">
					<xsl:value-of select="printsex" />
				</fo:block>
			</fo:table-cell>
			<fo:table-cell>
				<fo:block font-family="mysimsun" text-align="center">
					<xsl:value-of select="printBirthday" />
				</fo:block>
			</fo:table-cell>
			<fo:table-cell>
				<fo:block font-family="mysimsun" text-align="center">
					<xsl:value-of select="printBirPla" />
				</fo:block>
			</fo:table-cell>
			<fo:table-cell>
				<fo:block font-family="mysimsun" text-align="center">
					<xsl:value-of select="printPassportNo" />
				</fo:block>
			</fo:table-cell>
			<fo:table-cell>
				<fo:block font-family="mysimsun" text-align="center">
					<xsl:value-of select="printPassportPla" />
				</fo:block>
			</fo:table-cell>
		</fo:table-row>
	</xsl:template>

	<!-- Header -->
	<xsl:template name="header">
		<fo:block>
			<fo:table table-layout="fixed" width="100%">
				<fo:table-column column-width="6.5cm" />
				<fo:table-column column-width="9cm" />
				<fo:table-body>
					<fo:table-row height="8.2mm">
						<fo:table-cell></fo:table-cell>
						<fo:table-cell>
							<fo:block font-family="mysimhei"
								space-after="5mm" text-align="left">
								<xsl:value-of select="tourNo" />
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
		</fo:block>
		<fo:block></fo:block>
		<fo:block>
			<fo:table table-layout="fixed" width="100%">
				<fo:table-column column-width="2.5cm" />
				<fo:table-column column-width="3cm" />
				<fo:table-column column-width="2cm" />
				<fo:table-column column-width="7.5cm" />
				<fo:table-body>
					<fo:table-row height="7.1mm">
						<fo:table-cell></fo:table-cell>
						<fo:table-cell>
							<fo:block font-family="mysimhei"
								space-after="3mm" text-align="left">
								<xsl:value-of select="leader" />
							</fo:block>
						</fo:table-cell>
						<fo:table-cell></fo:table-cell>
						<fo:table-cell>
							<fo:block font-family="mysimhei"
								space-after="3mm" text-align="left">
								<xsl:value-of select="leadnum" />
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
		</fo:block>
		<fo:block>
			<fo:table table-layout="fixed" width="100%">
			<fo:table-column column-width="5mm" />
			    <fo:table-column column-width="3.5cm" />
				<fo:table-column column-width="1.6cm" />
				<fo:table-column column-width="1.6cm" />
				<fo:table-column column-width="2.0cm" />

            
				<fo:table-column column-width="3.5cm" />
				<fo:table-column column-width="1.6cm" />
				<fo:table-column column-width="1.6cm" />
				<fo:table-column column-width="1.9cm" />

				<fo:table-body>
					<fo:table-row height="6.85mm">
					<fo:table-cell></fo:table-cell>
						<fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="9" text-align="left">
								<xsl:value-of select="date1" />
							</fo:block>
						</fo:table-cell>

						<fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="9" text-align="left">
								<xsl:value-of select="pla1" />
							</fo:block>
						</fo:table-cell>
						<fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="9" text-align="left">
								<xsl:value-of select="pass1" />
							</fo:block>
						</fo:table-cell>
						<fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="9" text-align="left">
								<xsl:value-of select="end1" />
							</fo:block>
						</fo:table-cell>
                      
						<fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="9" text-align="left">
								<xsl:value-of select="date2" />
							</fo:block>
						</fo:table-cell>
						<fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="9" text-align="left">
								<xsl:value-of select="pla2" />
							</fo:block>
						</fo:table-cell>
						<fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="9" text-align="left">
								<xsl:value-of select="pass2" />
							</fo:block>
						</fo:table-cell>
						<fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="9" text-align="left">
								<xsl:value-of select="end2" />
							</fo:block>
						</fo:table-cell>
					</fo:table-row>

					<fo:table-row height="6.85mm">
					<fo:table-cell></fo:table-cell>
					 <fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="9" text-align="left">
								<xsl:value-of select="date3" />
							</fo:block>
						</fo:table-cell>
						<fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="9" text-align="left">
								<xsl:value-of select="pla3" />
							</fo:block>
						</fo:table-cell>
						<fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="9" text-align="left">
								<xsl:value-of select="pass3" />
							</fo:block>
						</fo:table-cell>
						<fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="9" text-align="left">
								<xsl:value-of select="end3" />
							</fo:block>
						</fo:table-cell>
					
						<fo:table-cell></fo:table-cell>
						<fo:table-cell></fo:table-cell>
						<fo:table-cell></fo:table-cell>
						<fo:table-cell></fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
		</fo:block>
	</xsl:template>

	<!-- Footer -->
	<xsl:template name="footer">
		<fo:block space-after="22mm"></fo:block>
		<fo:block>
			<fo:table table-layout="fixed" width="100%">
				<fo:table-column column-width="3cm" />
				<fo:table-column column-width="3.8cm" />
				<fo:table-column column-width="2.6cm" />
				<fo:table-column column-width="2cm" />
				<fo:table-column column-width="5.7cm" />

				<fo:table-body>
					<fo:table-row height="6.85mm"><!-- 7mm -->
						<fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="11">
								<xsl:value-of select="arrhkdate" />
							</fo:block>
						</fo:table-cell>
						<fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="11">
								<xsl:value-of select="arrhktime" />
							</fo:block>
						</fo:table-cell>

						<fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="11">
								<xsl:value-of select="leavehkdate" />
							</fo:block>
						</fo:table-cell>
						<fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="11">
								<xsl:value-of select="leavehktime" />
							</fo:block>
						</fo:table-cell>

						<fo:table-cell>
							<fo:block font-size="10pt"
								font-weight="bold" font-family="mysimsun" space-after="5mm"
							 text-align="right">
								<xsl:value-of select="male" />
							</fo:block>
						</fo:table-cell>
					</fo:table-row>

					<fo:table-row height="7.85"><!-- 8mm -->
						<fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="11" text-align="right">
								<xsl:value-of select="arrhkvehicle" />
							</fo:block>
						</fo:table-cell>
						<fo:table-cell></fo:table-cell>
						<fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="11" text-align="right">
								<xsl:value-of select="leavehkvehicle" />
							</fo:block>
						</fo:table-cell>
						<fo:table-cell></fo:table-cell>
						<fo:table-cell>
							<fo:block font-size="10pt"
								font-weight="bold" font-family="mysimsun" space-after="5mm"
								text-align="right">
								<xsl:value-of select="female" />
							</fo:block>
						</fo:table-cell>
					</fo:table-row>

					<fo:table-row height="6.85mm"><!-- 7mm -->
						<fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="11">
								<xsl:value-of select="arrmcdate" />
							</fo:block>
						</fo:table-cell>
						<fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="11">
								<xsl:value-of select="arrmctime" />
							</fo:block>
						</fo:table-cell>
						<fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="11">
								<xsl:value-of select="leavemcdate" />
							</fo:block>
						</fo:table-cell>
						<fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="11">
								<xsl:value-of select="leavemctime" />
							</fo:block>
						</fo:table-cell>

						<fo:table-cell>
							<fo:block font-size="10pt"
								font-weight="bold" font-family="mysimsun" space-after="5mm"
								text-align="right">
								<xsl:value-of select="child" />
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row height="7.85mm"><!-- 8mm -->
						<fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="11" text-align="right">
								<xsl:value-of select="arrmcvehicle" />
							</fo:block>
						</fo:table-cell>
						<fo:table-cell></fo:table-cell>
						<fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="11" text-align="right">
								<xsl:value-of select="leavemcvehicle" />
							</fo:block>
						</fo:table-cell>
						<fo:table-cell></fo:table-cell>
						<fo:table-cell>
							<fo:block font-size="10pt"
								font-weight="bold" font-family="mysimsun" space-after="5mm"
								text-align="right">
								<xsl:value-of select="pax" />
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
		</fo:block>
		<!--  -->
		<fo:block space-after="7mm"></fo:block>
		<fo:block>
			<fo:table>
				<fo:table-column column-width="24%" />
				<fo:table-column column-width="32%" />
				<fo:table-column column-width="25%" />
				<fo:table-column column-width="33%" />
				<fo:table-body>
					<fo:table-row height="6.85mm"><!-- 7mm -->
						<fo:table-cell></fo:table-cell>
						<fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="10">
								<xsl:value-of select="localtname" />
							</fo:block>
						</fo:table-cell>
						<fo:table-cell></fo:table-cell>
						<fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="10">
								<xsl:value-of select="localecon" />
							</fo:block>
						</fo:table-cell>
					</fo:table-row>

					<fo:table-row height="6.85mm"><!-- 7mm -->
						<fo:table-cell></fo:table-cell>
						<fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="10">
								<xsl:value-of select="hktname" />
							</fo:block>
						</fo:table-cell>
						<fo:table-cell></fo:table-cell>
						<fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="10">
								<xsl:value-of select="hkecon" />
							</fo:block>
						</fo:table-cell>
					</fo:table-row>

					<fo:table-row height="6.85mm"><!-- 7mm -->
						<fo:table-cell></fo:table-cell>
						<fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="10">
								<xsl:value-of select="mctname" />
							</fo:block>
						</fo:table-cell>
						<fo:table-cell></fo:table-cell>
						<fo:table-cell>
							<fo:block font-family="mysimsun"
								font-size="10">
								<xsl:value-of select="mcecon" />
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
		</fo:block>
	</xsl:template>

</xsl:stylesheet>

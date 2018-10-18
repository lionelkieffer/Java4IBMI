<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ws="http://www.wynnesystems.com/AxiomDocument"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

	<!-- -->
	<xsl:template match="/">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="simpleA4"
					page-height="29.7cm" page-width="21cm" margin-top="2cm"
					margin-bottom="2cm" margin-left="2cm" margin-right="2cm" font-family="Helvetica">
					<fo:region-body />
				</fo:simple-page-master>
			</fo:layout-master-set>
			<xsl:apply-templates select="SYSTEM_INFO" />
		</fo:root>
	</xsl:template>

	<xsl:template match="SYSTEM_INFO">
		<fo:page-sequence master-reference="simpleA4">
		<fo:title>System report</fo:title> 
			<fo:flow flow-name="xsl-region-body">
			    <fo:block text-align="right" space-after="5mm">
					<fo:external-graphic src="./resources/img/smiley.png" content-height="60px" content-width="60px" />
				</fo:block>
				<fo:block border="solid 0.1mm blue" width="50%" space-after="5mm" background-color = "#6fbcbc">
					Report on system <xsl:value-of select="@HOST_NAME" />
				</fo:block>
				<fo:block font-size="12pt" space-after="5mm">
					<fo:block font-weight="bold" space-after="5mm">
					Some technical details:
					</fo:block>
					<!-- list start -->
					<fo:list-block >
						<!-- list item -->
						<fo:list-item>
						<!-- insert a bullet -->
							<fo:list-item-label end-indent="label-end()">
								<fo:block>
									<fo:inline font-family="Symbol">&#x2022;</fo:inline>
								</fo:block>
							</fo:list-item-label>
							<!-- list text -->
							<fo:list-item-body start-indent="body-start()">
								<fo:block>
									Maximum jobs possible: <xsl:value-of select="MAXIMUM_JOBS_IN_SYSTEM" />
								</fo:block>
							</fo:list-item-body>
						</fo:list-item>
						<!-- list item -->
						<fo:list-item>
						<!-- insert a bullet -->
							<fo:list-item-label end-indent="label-end()">
								<fo:block>
									<fo:inline font-family="Symbol">&#x2022;</fo:inline>
								</fo:block>
							</fo:list-item-label>
							<!-- list text -->
							<fo:list-item-body start-indent="body-start()">
								<fo:block>
									Current CPU capacity: <xsl:value-of select="CURRENT_CPU_CAPACITY" />
								</fo:block>
							</fo:list-item-body>
						</fo:list-item>
						<!-- list item -->
						<fo:list-item>
						<!-- insert a bullet -->
							<fo:list-item-label end-indent="label-end()">
								<fo:block>
									<fo:inline font-family="Symbol">&#x2022;</fo:inline>
								</fo:block>
							</fo:list-item-label>
							<!-- list text -->
							<fo:list-item-body start-indent="body-start()">
								<fo:block>
									System ASP storage: <xsl:value-of select="SYSTEM_ASP_STORAGE" />
								</fo:block>
							</fo:list-item-body>
						</fo:list-item>
					</fo:list-block>
					<!-- end list -->
				</fo:block>
				<fo:block font-weight="bold" space-after="5mm">Used ASP space:</fo:block>
				<fo:block>
				<fo:instream-foreign-object xmlns:svg="http://www.w3.org/2000/svg">
				<svg:svg width="500" height="50">
					<svg:rect width="400" height="40" style="fill:rgb(190, 201, 229);stroke-width:1;stroke:rgb(0,0,0)" />
					<svg:rect height="40" style="fill:rgb(229, 156, 156);stroke-width:1;stroke:rgb(50,50,50)">
						<xsl:attribute name="width"><xsl:value-of select="4 * SYSTEM_ASP_USED" /></xsl:attribute>
					</svg:rect>
					<svg:text x="15" y="25" fill="white" stroke="black" font-size="20px"><xsl:value-of select="SYSTEM_ASP_USED" />%</svg:text>
				</svg:svg>
				</fo:instream-foreign-object>
				</fo:block> 
				<fo:block font-weight="bold" space-after="5mm">System values:</fo:block>
				<fo:block font-size="10pt">				
					<fo:table table-layout="fixed" width="100%"
						border-collapse="separate"
						border="solid 0.1mm black">
						<fo:table-column column-width="4cm" />
						<fo:table-column />
						<fo:table-body>
							<xsl:apply-templates select="SYSTEM_VALUES/SYSTEM_VALUE" />
						</fo:table-body>
					</fo:table>
				</fo:block>
			</fo:flow>
		</fo:page-sequence>
	</xsl:template>

	<xsl:template match="SYSTEM_VALUES/SYSTEM_VALUE">
		<fo:table-row>
			<xsl:if test="@NAME='QSYSLIBL'">
				<xsl:attribute name="font-weight">bold</xsl:attribute>
				<xsl:attribute name="color">red</xsl:attribute>
			</xsl:if>
			<fo:table-cell border="solid 0.1mm grey" background-color="#b8cef2">
				<fo:block>
					<xsl:value-of select="@NAME" />
				</fo:block>
			</fo:table-cell>

			<fo:table-cell border="solid 0.1mm grey">
				<fo:block>
					<xsl:value-of select="VALUE" />
				</fo:block>
			</fo:table-cell>
		</fo:table-row>
	</xsl:template>
	
</xsl:stylesheet>
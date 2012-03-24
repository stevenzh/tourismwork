<?xml version="1.0" encoding="UTF-8"?>
<!-- $Id: InShortReckoningPrint.xsl,v 1.1 2009/03/01 16:24:16 zhangst Exp $ -->
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">
  <xsl:output method="xml" version="1.0" omit-xml-declaration="no" indent="yes" />
  <xsl:param name="versionParam" select="'1.0'" />

  <!-- ========================= -->
  <!--       国内短线帐单打印        -->
  <!-- ========================= -->
  <xsl:template match="reckoning">
   <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format" language="zh">
     <fo:layout-master-set>
        <fo:simple-page-master master-name="simpleA4" page-height="29.7cm" page-width="21cm" margin-top="2cm" margin-bottom="2cm" margin-left="2cm" margin-right="2cm">
          <fo:region-body/>
        </fo:simple-page-master>
      </fo:layout-master-set>
       <fo:page-sequence master-reference="simpleA4">
        <fo:flow flow-name="xsl-region-body">
  
        </fo:flow>
      </fo:page-sequence>
 
   </fo:root>
 </xsl:template>
</xsl:stylesheet>
<?xml version="1.0" encoding="UTF-8"?>
<!-- $Id: OutBandPrint.xsl,v 1.1 2009/03/01 16:24:15 zhangst Exp $ -->
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">
  
  <xsl:output method="xml" version="1.0" omit-xml-declaration="no" indent="yes" />
  <xsl:param name="versionParam" select="'1.0'" />

  <!-- ========================= -->
  <!--         境外报团名单表        -->
  <!-- ========================= -->
  <xsl:template match="tour">
    <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format" language="zh">
      <fo:layout-master-set>
        <fo:simple-page-master master-name="simpleA4" page-height="29.7cm" page-width="21cm" margin-top="2cm" margin-bottom="2cm" margin-left="2cm" margin-right="2cm">
          <fo:region-body />
        </fo:simple-page-master>
      </fo:layout-master-set>
      <fo:page-sequence master-reference="simpleA4">
        <fo:flow flow-name="xsl-region-body">
          <fo:block font-size="12pt" font-weight="bold" linefeed-treatment="preserve" white-space-treatment="preserve" font-family="mysimsun" space-after="5mm">
            <xsl:value-of select="label1"/>
          </fo:block>
          <fo:block font-family="mysimsun" font-weight="bold">麻烦请安排下列团队地接：</fo:block>
          <fo:block font-size="12pt" font-weight="bold" linefeed-treatment="preserve" white-space-treatment="preserve" font-family="mysimsun" space-after="5mm">
            <xsl:value-of select="label2"/>
          </fo:block>
          <fo:block font-size="15pt" font-family="mysimhei" space-after="5mm" width="100%">人员名单：</fo:block>

          <!-- 先左后右 -->
          <fo:block font-size="12pt">
            <fo:table table-layout="fixed" width="100%">
              <fo:table-column column-width="5%" />
              <fo:table-column column-width="10%" />
              <fo:table-column column-width="20%" />
              <fo:table-column column-width="10%" />
              <fo:table-column column-width="5%" />
              <fo:table-column column-width="5%" />
              <fo:table-column column-width="10%" />
              <fo:table-column column-width="20%" />
              <fo:table-column column-width="10%" />
              <fo:table-column column-width="5%" />
              <fo:table-body>
                <xsl:for-each select="customer">
                  <xsl:if test="count(//customer) div 2 + count(//customer) mod 2>= position()">
                    <xsl:call-template name="count">
                      <xsl:with-param name="cols" select="position()" />
                    </xsl:call-template>
                  </xsl:if>
                </xsl:for-each>
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block font-size="12pt" font-weight="bold" linefeed-treatment="preserve" white-space-treatment="preserve" font-family="mysimsun" space-after="5mm" space-before="5mm" white-space-collapse="false">
            <xsl:value-of select="label3"/>
          </fo:block>
        </fo:flow>
      </fo:page-sequence>
    </fo:root>
  </xsl:template>
  
  
  <xsl:template name="count">
    <fo:table-row  height="5.5mm">
      <xsl:for-each select="//customer[$cols]">
        <fo:table-cell>
          <fo:block font-family="mysimsun">
            <xsl:value-of select="idx" />
          </fo:block>
        </fo:table-cell>
        <fo:table-cell>
          <fo:block font-family="mysimsun">
            <xsl:value-of select="name" />
          </fo:block>
        </fo:table-cell>
        <fo:table-cell>
          <fo:block font-family="mysimsun">
            <xsl:value-of select="name2" />
          </fo:block>
        </fo:table-cell>
        <fo:table-cell>
          <fo:block font-family="mysimsun">
            <xsl:value-of select="sex" />
          </fo:block>
        </fo:table-cell>
       <fo:table-cell >
        <fo:block font-family="mysimsun">
          <xsl:value-of select="child" />
        </fo:block>
       </fo:table-cell>
      </xsl:for-each>
      <xsl:for-each select="//customer[$cols + count(//customer) div 2 + count(//customer) mod 2]">
        <fo:table-cell>
          <fo:block font-family="mysimsun">
            <xsl:value-of select="idx" />
          </fo:block>
        </fo:table-cell>
        <fo:table-cell>
          <fo:block font-family="mysimsun">
            <xsl:value-of select="name" />
          </fo:block>
        </fo:table-cell>
        <fo:table-cell>
          <fo:block font-family="mysimsun">
            <xsl:value-of select="name2" />
          </fo:block>
        </fo:table-cell>
        <fo:table-cell>
          <fo:block font-family="mysimsun">
            <xsl:value-of select="sex" />
          </fo:block>
        </fo:table-cell>
        <fo:table-cell >
        <fo:block font-family="mysimsun">
          <xsl:value-of select="child" />
        </fo:block>
      </fo:table-cell>
      </xsl:for-each>
    </fo:table-row>
  </xsl:template>
  
  
  
<!--   奇数在左，偶数在右 
  <fo:block font-size="12pt">
            <fo:table table-layout="fixed" width="100%">
              <fo:table-column column-width="5%" />
              <fo:table-column column-width="10%" />
              <fo:table-column column-width="20%" />
              <fo:table-column column-width="15%" />
              <fo:table-column column-width="5%" />
              <fo:table-column column-width="10%" />
              <fo:table-column column-width="20%" />
              <fo:table-column column-width="15%" />
              <fo:table-body>
                <xsl:for-each select="customer">
                  <xsl:if test="position() mod 2 = 1">
                    <xsl:call-template name="count">
                      <xsl:with-param name="cols" select="position()" />
                    </xsl:call-template>
                  </xsl:if>
                </xsl:for-each>
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block font-size="12pt" font-weight="bold" linefeed-treatment="preserve" white-space-treatment="preserve" font-family="mysimsun" space-after="5mm" space-before="5mm" white-space-collapse="false">
            <xsl:value-of select="label3"/>
          </fo:block>
        </fo:flow>
      </fo:page-sequence>
    </fo:root>
  </xsl:template>
  
  
  <xsl:template name="count">
    <xsl:param name="cols" select=""></xsl:param>
    <fo:table-row  height="5.5mm">
      <xsl:for-each select="//customer[$cols]">
        <fo:table-cell>
          <fo:block font-family="mysimsun">
            <xsl:value-of select="idx" />
          </fo:block>
        </fo:table-cell>
        <fo:table-cell>
          <fo:block font-family="mysimsun">
            <xsl:value-of select="name" />
          </fo:block>
        </fo:table-cell>
        <fo:table-cell>
          <fo:block font-family="mysimsun">
            <xsl:value-of select="name2" />
          </fo:block>
        </fo:table-cell>
        <fo:table-cell>
          <fo:block font-family="mysimsun">
            <xsl:value-of select="sex" />
          </fo:block>
        </fo:table-cell>
      </xsl:for-each>
      <xsl:for-each select="//customer[$cols+1]">
        <fo:table-cell>
          <fo:block font-family="mysimsun">
            <xsl:value-of select="idx" />
          </fo:block>
        </fo:table-cell>
        <fo:table-cell>
          <fo:block font-family="mysimsun">
            <xsl:value-of select="name" />
          </fo:block>
        </fo:table-cell>
        <fo:table-cell>
          <fo:block font-family="mysimsun">
            <xsl:value-of select="name2" />
          </fo:block>
        </fo:table-cell>
        <fo:table-cell>
          <fo:block font-family="mysimsun">
            <xsl:value-of select="sex" />
          </fo:block>
        </fo:table-cell>
      </xsl:for-each>
    </fo:table-row>
  </xsl:template>
-->
  

</xsl:stylesheet>


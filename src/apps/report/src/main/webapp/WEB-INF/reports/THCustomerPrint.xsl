<?xml version="1.0" encoding="UTF-8"?>
<!-- $Id: THCustomerPrint.xsl,v 1.1 2009/03/01 16:24:15 zhangst Exp $ -->
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">
  <xsl:output method="xml" version="1.0" omit-xml-declaration="no" indent="yes" />
  <xsl:param name="versionParam" select="'1.0'" />

  <!-- ========================= -->
  <!--         同业打印名单          -->
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
          <fo:block font-size="9pt" font-weight="bold" font-family="mysimkai" space-after="5mm">
            <xsl:value-of select="simpleReport" />
          </fo:block>
          <fo:block font-size="15pt" font-family="mysimhei" space-after="5mm" width="100%" text-align="center">旅行社</fo:block>
          <fo:block font-size="10pt" font-weight="bold" font-family="mysimkai" space-after="5mm" text-align="center">
            No.
            <xsl:value-of select="tour-name" />
            线路名:
            <xsl:value-of select="line-name" />
           出发时间:
            <xsl:value-of select="out-date" />
          </fo:block>
          <fo:block font-size="10pt" font-weight="bold" font-family="mysimkai" space-after="5mm">
           人数:
          	<xsl:value-of select="pax"/>
          </fo:block>

          <!-- <fo:block font-size="9pt">
            <fo:table table-layout="fixed" width="100%" border-color="black" border-style="solid" border-width=".5pt">
              <fo:table-column column-width="5%" />
              <fo:table-column column-width="95%" />
              <fo:table-body>
                <xsl:apply-templates select="tourinfo" />
              </fo:table-body>
            </fo:table>
          </fo:block>
 			-->
          <!-- <fo:block font-size="11pt" font-family="mysimsun" font-weight="bold" space-after="5mm" background-color="gray">游客资料</fo:block> -->
          <fo:block font-size="9pt">
            <fo:table table-layout="fixed" width="100%">
              <fo:table-column />
              <fo:table-column />
              <fo:table-column />
              <fo:table-column />
              <fo:table-column />
              <fo:table-column />
              <fo:table-column />
              <fo:table-column />
              <fo:table-column />
              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block font-family="mysimsun" font-weight="bold" background-color="gray">序号</fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block font-family="mysimsun" font-weight="bold" background-color="gray">中文姓名</fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block font-family="mysimsun" font-weight="bold" background-color="gray">英文姓名</fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block font-family="mysimsun" font-weight="bold" background-color="gray">性别</fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block font-family="mysimsun" font-weight="bold" background-color="gray">出生日期</fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block font-family="mysimsun" font-weight="bold" background-color="gray">出生地</fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block font-family="mysimsun" font-weight="bold" background-color="gray">护照号码</fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block font-family="mysimsun" font-weight="bold" background-color="gray">签发日期</fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block font-family="mysimsun" font-weight="bold" background-color="gray">签发地</fo:block>
                  </fo:table-cell>
                  <!-- <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block font-family="mysimsun" font-weight="bold" background-color="gray">有效日期</fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block font-family="mysimsun" font-weight="bold" background-color="gray">备注</fo:block>
                  </fo:table-cell>
                  <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block font-family="mysimsun" font-weight="bold" background-color="gray">客源</fo:block>
                  </fo:table-cell> -->
                </fo:table-row>
                <xsl:apply-templates select="customer" />
              </fo:table-body>
            </fo:table>
          </fo:block>

        </fo:flow>
      </fo:page-sequence>
    </fo:root>
  </xsl:template>

  <!-- ========================= -->
  <!-- child element: schedule   -->
  <!-- ========================= -->
  <!-- <xsl:template match="tourinfo">

    <fo:table-row width="100%">
      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt" border-bottom-style="none">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="tour-name" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="line-name" />
        </fo:block>
      </fo:table-cell>
    </fo:table-row>
    <fo:table-row>
      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt" border-top-style="none">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="out-date" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="in-date" />
        </fo:block>
      </fo:table-cell>
    </fo:table-row>
    <fo:table-row>
      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt" border-top-style="none">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="out-city" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="in-city" />
        </fo:block>
      </fo:table-cell>
    </fo:table-row>

  </xsl:template> -->

  <!-- ========================= -->
  <!-- child element: member     -->
  <!-- ========================= -->

  <xsl:template match="customer">
    <fo:table-row>
      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="idx" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="name" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="name3" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="sex" />
        </fo:block>
      </fo:table-cell>
       <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="birdate" />
        </fo:block>
      </fo:table-cell>
       <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="birpla" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="passportNo" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="passportDate" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="passportPla" />
        </fo:block>
      </fo:table-cell>
    </fo:table-row>
  </xsl:template>


</xsl:stylesheet>

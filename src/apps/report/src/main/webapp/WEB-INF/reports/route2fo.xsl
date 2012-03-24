<?xml version="1.0" encoding="UTF-8"?>
<!-- $Id: route2fo.xsl,v 1.1 2009/03/01 16:24:16 zhangst Exp $ -->
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">
  <xsl:output method="xml" version="1.0" omit-xml-declaration="no" indent="yes" />
  <xsl:param name="versionParam" select="'1.0'" />

  <!-- ========================= -->
  <!-- root element: projectteam -->
  <!-- ========================= -->
  <xsl:template match="route">
    <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format" language="zh">
      <fo:layout-master-set>
        <fo:simple-page-master master-name="simpleA4" page-height="29.7cm" page-width="21cm" margin-top="2cm" margin-bottom="2cm" margin-left="2cm" margin-right="2cm">
          <fo:region-body />
        </fo:simple-page-master>
      </fo:layout-master-set>

      <fo:page-sequence master-reference="simpleA4">
        <fo:flow flow-name="xsl-region-body">
          <fo:block font-size="14pt" font-weight="bold" font-family="mysimkai" space-after="5mm">
            Project:
            <xsl:value-of select="route-name" />
          </fo:block>
          <fo:block font-size="10.5pt" font-family="mysimhei" space-after="5mm" width="100%" background-color="blue">观光行程</fo:block>
          <fo:block font-size="9pt">
            <fo:table table-layout="fixed" width="100%" border-color="black" border-style="solid" border-width=".5pt">
              <fo:table-column column-width="5%" />
              <fo:table-column column-width="95%" />
              <fo:table-body>
                <xsl:apply-templates select="schedules" />
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block font-size="9pt" font-family="mysimsun" color="blue" space-after="3pt">※ 以上行程仅供出发前旅客参考，正确行程、航班及旅馆依 行前说明会资料/出发前通知 为准</fo:block>
          <fo:block font-size="9pt" font-family="mysimsun" space-after="3pt">注意：重申欧盟加强查缉仿冒品，旅客携带或穿着或购买仿冒商品,可处四年有期徒刑及37万3千美金罚款</fo:block>

          <fo:block font-size="9pt">
            <fo:table table-layout="fixed" width="100%">
              <fo:table-column column-width="5%" />
              <fo:table-column column-width="95%" />
              <fo:table-body>
                <xsl:apply-templates select="expenseIn" />
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block font-size="9pt" font-family="mysimsun" font-weight="bold" space-after="5mm">一、报价包含：</fo:block>
        </fo:flow>
      </fo:page-sequence>
    </fo:root>
  </xsl:template>

  <!-- ========================= -->
  <!-- child element: schedule   -->
  <!-- ========================= -->
  <xsl:template match="schedules">
    <fo:table-row width="100%">
      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt" border-bottom-style="none">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="day" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="traffic" />
        </fo:block>
      </fo:table-cell>
    </fo:table-row>
    <fo:table-row>
      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt" border-top-style="none">
        <fo:block font-family="mysimsun"></fo:block>
      </fo:table-cell>
      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="program" />
        </fo:block>
      </fo:table-cell>

    </fo:table-row>
  </xsl:template>

  <!-- ========================= -->
  <!-- child element: member     -->
  <!-- ========================= -->
  <xsl:template match="expenseIn">
    <fo:table-row width="100%">
      <fo:table-cell>
        <fo:block font-family="mysimsun">
          <xsl:value-of select="id" />
          .
        </fo:block>
      </fo:table-cell>
      <fo:table-cell>
        <fo:block font-family="mysimsun">
          <xsl:value-of select="item" />
        </fo:block>
      </fo:table-cell>
    </fo:table-row>
  </xsl:template>


</xsl:stylesheet>

<?xml version="1.0" encoding="UTF-8"?>
<!-- $Id: CustomerListPrint.xsl,v 1.1 2009/03/01 16:24:16 zhangst Exp $ -->
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">
  <xsl:output method="xml" version="1.0" omit-xml-declaration="no" indent="yes" />
  <xsl:param name="versionParam" select="'1.0'" />

  <!-- ========================= -->
  <!--          客人名单打印        -->
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
          <fo:table width="100%">
            <fo:table-column column-width="20%" />
            <fo:table-column column-width="80%" />
            <fo:table-body>
              <fo:table-row>
                <fo:table-cell>
                  <fo:block>
                    <fo:external-graphic src="./images/CTT65_64.GIF" />
                  </fo:block>
                </fo:table-cell>
                <fo:table-cell>
                  <fo:block font-size="15pt" font-family="mysimhei" space-after="5mm" text-align="center">旅行社</fo:block>
                  <fo:block font-size="15pt" font-family="mysimhei" space-after="3mm" text-align="center">Travel Service</fo:block>
                </fo:table-cell>
              </fo:table-row>
            </fo:table-body>
          </fo:table>
          <!-- <fo:block font-size="11pt" font-family="mysimhei" space-after="5mm" width="100%" background-color="gray">团信息</fo:block>-->
          <fo:table>
            <fo:table-column column-width="50%" />
            <fo:table-column column-width="50%" />
            <fo:table-body>
              <fo:table-row>
                <fo:table-cell>
                  <fo:block font-size="11pt" font-family="mysimsun" text-align="left">
                    发件人:
                    <xsl:value-of select="send" />
                  </fo:block>
                </fo:table-cell>
                <fo:table-cell>
                  <fo:block font-size="11pt" font-family="mysimsun" text-align="right">
                    收件人:
                    <xsl:value-of select="receive" />
                  </fo:block>
                </fo:table-cell>
              </fo:table-row>
            </fo:table-body>
          </fo:table>
          <fo:block font-size="15pt" font-family="mysimhei" text-align="center" font-weight="bold" space-after="5mm">
            <xsl:value-of select="file-title" />
          </fo:block>
          <fo:block font-size="11pt" font-weight="bold" font-family="mysimsun" space-after="5mm" linefeed-treatment="preserve" white-space-treatment="preserve">
            <xsl:value-of select="title" />
          </fo:block>
          <fo:block font-size="11pt" font-weight="bold" font-family="mysimsun" space-after="5mm">
            线路:
            <xsl:value-of select="line-name" />
            团号:
            <xsl:value-of select="tour-name" />
          </fo:block>
          <fo:block font-size="11pt" font-weight="bold" font-family="mysimsun" space-after="5mm">
            人数:
            <xsl:value-of select="pax" />
            领队:
            <xsl:value-of select="leadpax" />
          </fo:block>

          <!--
            <fo:block font-size="10pt" font-weight="bold" font-family="mysimkai" space-after="5mm">
            出境时间:
            <xsl:value-of select="out-date" />
            入境时间：
            <xsl:value-of select="in-date" />
            </fo:block>
          -->
          <fo:block font-size="9pt">
            <fo:table table-layout="fixed" width="100%" border-color="black" border-style="solid" border-width=".5pt">
              <fo:table-column column-width="5%" />
              <fo:table-column column-width="95%" />
              <fo:table-body>
                <xsl:apply-templates select="tourinfo" />
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block font-size="11pt" font-family="mysimhei" space-after="5mm" width="100%" alignment-baseline="center">客人名单</fo:block>

          <fo:block font-size="10pt">
            <fo:table table-layout="fixed" width="100%">
              <fo:table-column column-width="4%"/>
              <xsl:choose>
                <xsl:when test="starts-with(printagent,'true')">
                  <fo:table-column column-width="16%"/>
                </xsl:when>
              </xsl:choose>

              <xsl:choose>
                <xsl:when test="starts-with(printname,'true')">
                  <fo:table-column column-width="8%"/>
                </xsl:when>
              </xsl:choose>

              <xsl:choose>
                <xsl:when test="starts-with(printpinyin,'true')">
                  <fo:table-column column-width="14%"/>
                </xsl:when>
              </xsl:choose>

              <xsl:choose>
                <xsl:when test="starts-with(printsex,'true')">
                  <fo:table-column column-width="4%"/>
                </xsl:when>
              </xsl:choose>

              <xsl:choose>
                <xsl:when test="starts-with(printage,'true')">
                  <fo:table-column column-width="9.5%"/>
                  <fo:table-column column-width="4%"/>
                </xsl:when>
              </xsl:choose>
              
              <xsl:choose>
                <xsl:when test="starts-with(printIdcard,'true')">
                  <fo:table-column column-width="17%"/>
                </xsl:when>
              </xsl:choose>

              <xsl:choose>
                <xsl:when test="starts-with(printPassportType,'true')">
                  <fo:table-column column-width="8.5%"/>
                </xsl:when>
              </xsl:choose>

              <xsl:choose>
                <xsl:when test="starts-with(printPassportNo,'true')">
                  <fo:table-column column-width="9%"/>
                </xsl:when>
              </xsl:choose>

              <xsl:choose>
                <xsl:when test="starts-with(printPassportDate,'true')">
                  <fo:table-column column-width="9.5%"/>
                </xsl:when>
              </xsl:choose>

              <xsl:choose>
                <xsl:when test="starts-with(printPassportDate,'true')">
                  <fo:table-column column-width="9.5%"/>
                </xsl:when>
              </xsl:choose>

              <xsl:choose>
                <xsl:when test="starts-with(printPassportPla,'true')">
                  <fo:table-column column-width="8%"/>
                </xsl:when>
              </xsl:choose>

              <xsl:choose>
                <xsl:when test="starts-with(printPassportAnnotation,'true')">
                  <fo:table-column column-width="6%"/>
                </xsl:when>
              </xsl:choose>

              <fo:table-body>
                <fo:table-row>
                  <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block font-family="mysimsun" font-weight="bold" font-size="8">序号</fo:block>
                  </fo:table-cell>

                  <xsl:choose>
                    <xsl:when test="starts-with(printagent,'true')">
                      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
                        <fo:block font-family="mysimsun" font-weight="bold" font-size="8">代理商</fo:block>
                      </fo:table-cell>
                    </xsl:when>
                  </xsl:choose>

                  <xsl:choose>
                    <xsl:when test="starts-with(printname,'true')">
                      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
                        <fo:block font-family="mysimsun" font-weight="bold" font-size="8">姓名</fo:block>
                      </fo:table-cell>
                    </xsl:when>
                  </xsl:choose>

                  <xsl:choose>
                    <xsl:when test="starts-with(printpinyin,'true')">
                      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
                        <fo:block font-family="mysimsun" font-weight="bold" font-size="8">拼音</fo:block>
                      </fo:table-cell>
                    </xsl:when>
                  </xsl:choose>

                  <xsl:choose>
                    <xsl:when test="starts-with(printsex,'true')">
                      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
                        <fo:block font-family="mysimsun" font-weight="bold" font-size="8">性别</fo:block>
                      </fo:table-cell>
                    </xsl:when>
                  </xsl:choose>

                  <xsl:choose>
                    <xsl:when test="starts-with(printage,'true')">
                      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
                        <fo:block font-family="mysimsun" font-weight="bold" font-size="8">出生日期</fo:block>
                      </fo:table-cell>
	                      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
	                        <fo:block font-family="mysimsun" font-weight="bold" font-size="8">年龄</fo:block>
	                      </fo:table-cell>
                    </xsl:when>
                  </xsl:choose>
                  
                  <xsl:choose>
                    <xsl:when test="starts-with(printIdcard,'true')">
                      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
                        <fo:block font-family="mysimsun" font-weight="bold" font-size="8">身份证</fo:block>
                      </fo:table-cell>
                    </xsl:when>
                  </xsl:choose>
                  
                  <xsl:choose>
                    <xsl:when test="starts-with(printPassportType,'true')">
                      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
                        <fo:block font-family="mysimsun" font-weight="bold" font-size="8">护照类型</fo:block>
                      </fo:table-cell>
                    </xsl:when>
                  </xsl:choose>

                  <xsl:choose>
                    <xsl:when test="starts-with(printPassportNo,'true')">
                      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
                        <fo:block font-family="mysimsun" font-weight="bold" font-size="8">护照号</fo:block>
                      </fo:table-cell>
                    </xsl:when>
                  </xsl:choose>

                  <xsl:choose>
                    <xsl:when test="starts-with(printPassportDate,'true')">
                      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
                        <fo:block font-family="mysimsun" font-weight="bold" font-size="8">签发日期</fo:block>
                      </fo:table-cell>
                    </xsl:when>
                  </xsl:choose>

                  <xsl:choose>
                    <xsl:when test="starts-with(printPassportDate,'true')">
                      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
                        <fo:block font-family="mysimsun" font-weight="bold" font-size="8">护照有效期</fo:block>
                      </fo:table-cell>
                    </xsl:when>
                  </xsl:choose>

                  <xsl:choose>
                    <xsl:when test="starts-with(printPassportPla,'true')">
                      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
                        <fo:block font-family="mysimsun" font-weight="bold" font-size="8">签发地点</fo:block>
                      </fo:table-cell>
                    </xsl:when>
                  </xsl:choose>

                  <xsl:choose>
                    <xsl:when test="starts-with(printPassportAnnotation,'true')">
                      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
                        <fo:block font-family="mysimsun" font-weight="bold" font-size="8">护照说明</fo:block>
                      </fo:table-cell>
                    </xsl:when>
                  </xsl:choose>
                </fo:table-row>

                <xsl:apply-templates select="customer" />
              </fo:table-body>
            </fo:table>
          </fo:block>

          <fo:block font-size="10pt" font-weight="bold" font-family="mysimkai" space-after="5mm" linefeed-treatment="preserve" white-space-treatment="preserve">
            <xsl:value-of select="remark" />
          </fo:block>
        </fo:flow>
      </fo:page-sequence>
    </fo:root>
  </xsl:template>

  <!-- ========================= -->
  <!--          团基本信息          -->
  <!-- ========================= -->
  <xsl:template match="tourinfo">
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
  </xsl:template>

  <!-- ========================= -->
  <!-- child element: member     -->
  <!-- ========================= -->
  <xsl:template match="customer">
    <fo:table-row>
      <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
        <fo:block font-size="10pt"  font-family="mysimsun">
          <xsl:value-of select="idx" />
        </fo:block>
      </fo:table-cell>

      <xsl:choose>
        <xsl:when test="starts-with(printagent,'true')">
          <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
            <fo:block font-size="10pt" font-family="mysimsun">
              <xsl:value-of select="agentname" />
            </fo:block>
          </fo:table-cell>
        </xsl:when>
      </xsl:choose>

      <xsl:choose>
        <xsl:when test="starts-with(printname, 'true')">
          <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
            <fo:block font-size="10pt"  font-family="mysimsun">
              <xsl:value-of select="name" />
            </fo:block>
          </fo:table-cell>
        </xsl:when>
      </xsl:choose>

      <xsl:choose>
        <xsl:when test="starts-with(printpinyin,'true')">
          <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
            <fo:block font-size="10pt"  font-family="mysimsun">
              <xsl:value-of select="name2" />
            </fo:block>
          </fo:table-cell>
        </xsl:when>
      </xsl:choose>

      <xsl:choose>
        <xsl:when test="starts-with(printsex,'true')">
          <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
            <fo:block font-size="10pt"  font-family="mysimsun">
              <xsl:value-of select="sex" />
            </fo:block>
          </fo:table-cell>
        </xsl:when>
      </xsl:choose>

      <xsl:choose>
        <xsl:when test="starts-with(printage,'true')">
          <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
            <fo:block font-size="8pt"  font-family="mysimsun">
              <xsl:value-of select="birdate" />
            </fo:block>
          </fo:table-cell>
          <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
            <fo:block font-size="8pt"  font-family="mysimsun">
              <xsl:value-of select="age" />
            </fo:block>
          </fo:table-cell>
        </xsl:when>
      </xsl:choose>
      
      <xsl:choose>
        <xsl:when test="starts-with(printidcard,'true')">
          <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
            <fo:block font-size="10pt"  font-family="mysimsun">
              <xsl:value-of select="idcard" />
            </fo:block>
          </fo:table-cell>
        </xsl:when>
      </xsl:choose>

      <xsl:choose>
        <xsl:when test="starts-with(printPassportType,'true')">
          <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
            <fo:block font-size="10pt"  font-family="mysimsun">
              <xsl:value-of select="passportType" />
            </fo:block>
          </fo:table-cell>
        </xsl:when>
      </xsl:choose>

      <xsl:choose>
        <xsl:when test="starts-with(printPassportNo,'true')">
          <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
            <fo:block font-size="8pt"  font-family="mysimsun">
              <xsl:value-of select="passportNo" />
            </fo:block>
          </fo:table-cell>
        </xsl:when>
      </xsl:choose>

      <xsl:choose>
        <xsl:when test="starts-with(printPassportDate,'true')">
          <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
            <fo:block font-size="8pt"  font-family="mysimsun">
              <xsl:value-of select="passportDate" />
            </fo:block>
          </fo:table-cell>
        </xsl:when>
      </xsl:choose>

      <xsl:choose>
        <xsl:when test="starts-with(printPassportDate,'true')">
          <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
            <fo:block font-size="8pt"  font-family="mysimsun">
              <xsl:value-of select="passportExpiry" />
            </fo:block>
          </fo:table-cell>
        </xsl:when>
      </xsl:choose>

      <xsl:choose>
        <xsl:when test="starts-with(printPassportPla,'true')">
          <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
            <fo:block font-size="10pt"  font-family="mysimsun">
              <xsl:value-of select="passportPla" />
            </fo:block>
          </fo:table-cell>
        </xsl:when>
      </xsl:choose>

      <xsl:choose>
        <xsl:when test="starts-with(printPassportAnnotation,'true')">
          <fo:table-cell border-color="black" border-style="solid" border-width=".5pt">
            <fo:block font-size="10pt"  font-family="mysimsun">
              <xsl:value-of select="passportAnnotation" />
            </fo:block>
          </fo:table-cell>
        </xsl:when>
      </xsl:choose>

    </fo:table-row>
  </xsl:template>
</xsl:stylesheet>

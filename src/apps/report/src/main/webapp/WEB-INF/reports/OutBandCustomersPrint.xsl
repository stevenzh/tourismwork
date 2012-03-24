<?xml version="1.0" encoding="UTF-8"?>
<!-- $Id: OutBandCustomersPrint.xsl,v 1.1.2.13 2009/08/25 08:40:44 zhangst Exp $ -->
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">
  <xsl:output method="xml" version="1.0" omit-xml-declaration="no" indent="yes" />
  <xsl:param name="versionParam" select="'1.0'" />

  <!-- ========================= -->
  <!--          出境名单表          -->
  <!-- ========================= -->
  <xsl:template match="tour">
    <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format" language="zh">
      <fo:layout-master-set>
        <fo:simple-page-master master-name="simpleA4" page-height="304mm" page-width="207mm"
                        margin-top="32mm" margin-bottom="14mm" margin-left="26mm" margin-right="2mm">
          <fo:region-body margin-top="26mm" margin-bottom="83mm" />
          <fo:region-before extent="10mm" />
          <fo:region-after extent="83mm" />
        </fo:simple-page-master>
      </fo:layout-master-set>

      <fo:page-sequence master-reference="simpleA4">
      
        <!-- Header -->
        <fo:static-content flow-name="xsl-region-before">
          <fo:block>
            <xsl:call-template name="header" />
          </fo:block>
        </fo:static-content>
        
        <!-- Footer -->
        <fo:static-content flow-name="xsl-region-after">
          <fo:block font-size="10pt">
            <xsl:call-template name="footer" />
          </fo:block>
        </fo:static-content>

        <fo:flow flow-name="xsl-region-body">
          <fo:block font-size="10pt">
            <fo:table table-layout="fixed" width="100%">
              <fo:table-column column-width="10mm" />
              <fo:table-column column-width="18mm" />
              <fo:table-column column-width="35mm" />
              <fo:table-column column-width="11mm" />
              <fo:table-column column-width="30mm" />
              <fo:table-column column-width="18mm" />
              <fo:table-column column-width="24mm" />
              <fo:table-column column-width="14mm" />
              <fo:table-column column-width="24mm" />

              <xsl:choose>
                <xsl:when test="starts-with(leaderKey,'True')">
                  <xsl:call-template name="leader" />
                </xsl:when>
              </xsl:choose>
              <fo:table-body>
                <xsl:apply-templates select="customer" />
              </fo:table-body>
            </fo:table>
          </fo:block>
        </fo:flow>

      </fo:page-sequence>
    </fo:root>
  </xsl:template>

  <!-- Leader -->
  <xsl:template name="leader">
    <fo:table-header>
      <fo:table-row height="7mm"> 
         <fo:table-cell>
           <fo:block />
         </fo:table-cell>
        <fo:table-cell display-align="center">
          <fo:block font-family="mysimsun">
            <xsl:value-of select="leaderPrintName" />
          </fo:block>
        </fo:table-cell>
        <fo:table-cell display-align="center">
          <fo:block font-family="mysimsun">
            <xsl:value-of select="leaderPrintPinyin" />
          </fo:block>
        </fo:table-cell>
        <fo:table-cell display-align="center">
          <fo:block font-family="mysimsun">
            <xsl:value-of select="leaderPrintSex" />
          </fo:block>
        </fo:table-cell>
        <fo:table-cell display-align="center">
          <fo:block font-family="mysimsun">
            <xsl:value-of select="leaderPrintBirthday" />
          </fo:block>
        </fo:table-cell>
        <fo:table-cell display-align="center">
          <fo:block font-family="mysimsun">
            <xsl:value-of select="leaderPrintBirPla" />
          </fo:block>
        </fo:table-cell>
        <fo:table-cell display-align="center">
          <fo:block font-family="mysimsun">
            <xsl:value-of select="leaderPrintPassportNo" />
          </fo:block>
        </fo:table-cell>
        <fo:table-cell display-align="center">
          <fo:block font-family="mysimsun">
            <xsl:value-of select="leaderPrintPassportPla" />
          </fo:block>
        </fo:table-cell>
        <fo:table-cell display-align="center">
          <fo:block font-family="mysimsun">
            <xsl:value-of select="leaderPrintPassportDate" />
          </fo:block>
        </fo:table-cell>
      </fo:table-row>
    </fo:table-header>
  </xsl:template>

  <!-- Customer -->
  <xsl:template match="customer">
    <fo:table-row height="7mm"> 
      <fo:table-cell><fo:block/></fo:table-cell>
      <fo:table-cell display-align="center">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="printname" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="printpinyin" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="printsex" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="printBirthday" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="printBirPla" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="printPassportNo" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="printPassportPla" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center"> 
        <fo:block font-family="mysimsun">
          <xsl:value-of select="printPassportDate" />
        </fo:block>
      </fo:table-cell>
    </fo:table-row>
  </xsl:template>

  <!-- Header -->
  <xsl:template name="header">
    <fo:table table-layout="fixed" width="100%">
      <fo:table-column column-width="24mm" />
      <fo:table-column column-width="34mm" />
      <fo:table-column column-width="38mm" />
      <fo:table-column column-width="41mm" />
      <fo:table-column column-width="44mm" />
      <fo:table-body>
        <fo:table-row height="6mm">
          <fo:table-cell>
            <fo:block/>
          </fo:table-cell>
          <fo:table-cell>
            <fo:block font-size="9pt" font-family="mysimhei">
              <xsl:value-of select="tourSerialNumber" />
            </fo:block>
          </fo:table-cell>
          <fo:table-cell>
            <fo:block font-size="9pt" font-family="mysimhei">
              <xsl:value-of select="tourNo" />
            </fo:block>
          </fo:table-cell>
          <fo:table-cell>
            <fo:block font-size="10pt" font-family="mysimhei">
              <xsl:value-of select="year" />
            </fo:block>
          </fo:table-cell>
          <fo:table-cell>
            <fo:block/>
          </fo:table-cell>
        </fo:table-row>
        <fo:table-row height="7mm">
          <fo:table-cell>
            <fo:block/>
          </fo:table-cell>
          <fo:table-cell>
            <fo:block font-size="10pt" font-family="mysimhei">
              <xsl:value-of select="leader" />
            </fo:block>
          </fo:table-cell>
          <fo:table-cell>
            <fo:block/>
          </fo:table-cell>
          <fo:table-cell>
            <fo:block/>
          </fo:table-cell>
          <fo:table-cell>
            <fo:block/>
          </fo:table-cell>
        </fo:table-row>
      </fo:table-body>
    </fo:table>
  </xsl:template>

  <!-- Footer -->
  <xsl:template name="footer">
    <fo:block space-after="47mm" space-before="4mm">
      <fo:table table-layout="fixed" width="100%">
        <fo:table-column column-width="4%" />
        <fo:table-column column-width="32%" />
        <fo:table-column column-width="12%" />
        <fo:table-column column-width="24%" />
        <fo:table-column column-width="10%" />
        <fo:table-column column-width="10%" />
        <fo:table-column column-width="8%" />

        <fo:table-body>
          <fo:table-row height="6mm">
            <fo:table-cell>
              <fo:block />
            </fo:table-cell>
            <fo:table-cell>
              <xsl:choose>
                <xsl:when test="starts-with(outInKey,'False')">
                  <fo:block font-family="mysimsun">
                    <xsl:value-of select="outDate" />
                  </fo:block>
                </xsl:when>
              </xsl:choose>
            </fo:table-cell>
            <fo:table-cell>
              <xsl:choose>
                <xsl:when test="starts-with(outInKey,'False')">
                  <fo:block font-family="mysimsun">
                    <xsl:value-of select="outCity" />
                  </fo:block>
                </xsl:when>
              </xsl:choose>
            </fo:table-cell>
            <fo:table-cell number-rows-spanned="2" display-align="center">
              <fo:block></fo:block>
            </fo:table-cell>
            <fo:table-cell number-rows-spanned="2" display-align="center">
              <fo:block font-family="mysimsun">
                <xsl:value-of select="printPax" />
              </fo:block>
            </fo:table-cell>
            <fo:table-cell number-rows-spanned="2" display-align="center">
              <fo:block font-family="mysimsun">
                <xsl:value-of select="printMalePax" />
              </fo:block>
            </fo:table-cell>
            <fo:table-cell number-rows-spanned="2" display-align="center">
              <fo:block font-family="mysimsun">
                <xsl:value-of select="printFemalePax" />
              </fo:block>
            </fo:table-cell>
          </fo:table-row>

          <fo:table-row height="6mm">
            <fo:table-cell>
              <fo:block />
            </fo:table-cell>
            <fo:table-cell>
              <xsl:choose>
                <xsl:when test="starts-with(outInKey,'False')">
                  <fo:block font-family="mysimsun">
                    <xsl:value-of select="inDate" />
                  </fo:block>
                </xsl:when>
              </xsl:choose>
            </fo:table-cell>
            <fo:table-cell>
              <xsl:choose>
                <xsl:when test="starts-with(outInKey,'False')">
                  <fo:block font-family="mysimsun">
                    <xsl:value-of select="inCity" />
                  </fo:block>
                </xsl:when>
              </xsl:choose>
            </fo:table-cell>
            
          </fo:table-row>
        </fo:table-body>
      </fo:table>
    </fo:block>

    <fo:block space-before="16mm">
      <fo:table table-layout="fixed" width="100%">
        <fo:table-column column-width="24mm" />
        <fo:table-column column-width="88mm" />
        <fo:table-column />
        <fo:table-body>
          <fo:table-row height="11mm">
            <fo:table-cell><fo:block/></fo:table-cell>
            <fo:table-cell>
              <fo:block font-family="mysimsun">
                <xsl:value-of select="routeName" />
              </fo:block>
            </fo:table-cell>
            <fo:table-cell>
              <fo:block font-family="mysimsun"></fo:block>
            </fo:table-cell>
          </fo:table-row>
          <fo:table-row height="6mm">
            <fo:table-cell><fo:block/></fo:table-cell>
            <fo:table-cell>
              <fo:block font-family="mysimsun">
                <xsl:value-of select="localTname" />
              </fo:block>
            </fo:table-cell>
            <fo:table-cell>
              <fo:block font-family="mysimsun">
                <xsl:value-of select="localEcontact" />
              </fo:block>
            </fo:table-cell>
          </fo:table-row>
          <fo:table-row height="6mm">
            <fo:table-cell><fo:block/></fo:table-cell>
            <fo:table-cell>
              <fo:block font-family="mysimsun">
                <xsl:value-of select="receptionTname" />
              </fo:block>
            </fo:table-cell>
            <fo:table-cell>
              <fo:block font-family="mysimsun">
                <xsl:value-of select="receptionEcontact" />
              </fo:block>
            </fo:table-cell>
          </fo:table-row>
        </fo:table-body>
      </fo:table>
    </fo:block>
  </xsl:template>

</xsl:stylesheet>
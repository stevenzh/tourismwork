<?xml version="1.0" encoding="UTF-8"?>
<!-- $Id: BillheadPrint.xsl,v 1.1 2009/03/01 16:24:16 zhangst Exp $ -->
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">
  
  <xsl:output method="xml" version="1.0" omit-xml-declaration="no" indent="yes" />
  <xsl:param name="versionParam" select="'1.0'" />

  <!-- ========================= -->
  <!--         付款申请书打印        -->
  <!-- ========================= -->
  <xsl:template match="billhead">
    <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format" language="zh">
      <fo:layout-master-set>
        <fo:simple-page-master master-name="simpleA4" page-height="29.7cm" page-width="21cm" margin-top="2cm" margin-bottom="2cm" margin-left="2cm" margin-right="2cm">
          <fo:region-body />
        </fo:simple-page-master>
      </fo:layout-master-set>
      <fo:page-sequence master-reference="simpleA4">
        <fo:flow flow-name="xsl-region-body">
          <fo:block font-size="18pt" font-family="mysimkai" font-weight="bold" text-align="center" space-after="3mm">付款申请书</fo:block>
          <fo:block font-size="12pt" font-weight="bold" linefeed-treatment="preserve" white-space-treatment="preserve" font-family="mysimsun">
             <fo:table table-layout="fixed" width="100%">
              <fo:table-column column-width="60%" />
              <fo:table-column column-width="40%" />
              
               <fo:table-body>
                <fo:table-row height="6mm">
                  <fo:table-cell>
                    <fo:block> 
                          致：<xsl:value-of select="supplierName"/>
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="right">
                         日期：<xsl:value-of select="opApprovedTime" />
                    </fo:block>
                  </fo:table-cell>
                 </fo:table-row>
               </fo:table-body>
              </fo:table>
          
           
          </fo:block>
          
          <fo:block font-size="12pt" font-weight="bold" linefeed-treatment="preserve" white-space-treatment="preserve" font-family="mysimsun">
          　　　<xsl:value-of select="note"/>
          </fo:block>

          <!-- 先左后右 -->
          <fo:block font-size="10pt">
            <fo:table table-layout="fixed" width="100%">
              <fo:table-column column-width="4%" />
              <fo:table-column column-width="32%" />
              <fo:table-column column-width="20%" />
              <fo:table-column column-width="7%" />
              <fo:table-column column-width="7%" />
              <fo:table-column column-width="15%" />
              <fo:table-column column-width="15%" />
              
              <fo:table-header>
                <fo:table-row height="6mm">
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-weight="bold" font-family="mysimsun">NO.</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-weight="bold" font-family="mysimsun">团号</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-weight="bold" font-family="mysimsun">内容</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-weight="bold" font-family="mysimsun">币种</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-weight="bold" font-family="mysimsun">汇率</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-weight="bold" font-family="mysimsun">外币金额</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-weight="bold" font-family="mysimsun">现付款(RMB)</fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-header>

              <fo:table-body>
                <xsl:apply-templates select="billheadItem" />
              </fo:table-body>
            </fo:table>
          </fo:block>
          
          
          <fo:block font-size="12pt" space-before="3mm">
            <fo:table table-layout="fixed" width="100%">
              <fo:table-column column-width="15%" />
              <fo:table-column column-width="35%" />
              <fo:table-column column-width="15%" />
              <fo:table-column column-width="35%" />

              <fo:table-body>
                <fo:table-row height="6mm">
                 <fo:table-cell>
                   <fo:block text-align="left" font-family="mysimsun">
                        付款合计：
                   </fo:block>
                 </fo:table-cell>
                 <fo:table-cell>
                   <fo:block text-align="left" font-family="mysimsun">
                      (RMB)<xsl:value-of select="amount" />元
                   </fo:block>
                 </fo:table-cell>
                 <fo:table-cell>
                   <fo:block text-align="left" font-family="mysimsun">
                      付款方式：
                   </fo:block>
                 </fo:table-cell>
                 <fo:table-cell>
                   <fo:block text-align="left" font-family="mysimsun">
                      <xsl:value-of select="payModeName" />
                   </fo:block>
                 </fo:table-cell>
                </fo:table-row>
                <fo:table-row height="6mm">
                 <fo:table-cell>
                   <fo:block text-align="left" font-family="mysimsun">
                        付款合计：
                   </fo:block>
                 </fo:table-cell>
                 <fo:table-cell number-columns-spanned="3">
                   <fo:block text-align="left" font-family="mysimsun">
                      人民币（大写）<xsl:value-of select="amountCn" />
                   </fo:block>
                 </fo:table-cell>
                </fo:table-row>
                
              </fo:table-body>
            </fo:table>
          </fo:block>
          
           <fo:block font-size="12pt" space-before="3mm">
            <fo:table table-layout="fixed" width="100%" >
              <fo:table-column column-width="15%" />
              <fo:table-column column-width="20%" />
              <fo:table-column column-width="19%" />
              <fo:table-column column-width="19%" />
              <fo:table-column column-width="27%" />

              <fo:table-body>
                <fo:table-row height="6mm">
                 <fo:table-cell>
                   <fo:block text-align="left" font-family="mysimsun">
                        经理：
                   </fo:block>
                 </fo:table-cell>
                 <fo:table-cell>
                   <fo:block text-align="left" font-family="mysimsun">
                        部门主管：
                   </fo:block>
                 </fo:table-cell>
                 <fo:table-cell>
                   <fo:block text-align="left" font-family="mysimsun">
                        经办人：<xsl:value-of select="frApprovedbyName" />
                   </fo:block>
                 </fo:table-cell>
                 <fo:table-cell>
                   <fo:block text-align="left" font-family="mysimsun">
                        借款人：<xsl:value-of select="opApprovedbyName" />
                   </fo:block>
                 </fo:table-cell>
                  <fo:table-cell>
                   <fo:block text-align="left" font-family="mysimsun">
                        部门：<xsl:value-of select="opApprovedbyDptName" />
                   </fo:block>
                 </fo:table-cell>
                </fo:table-row>
              </fo:table-body>
            </fo:table>

          </fo:block>
          
        </fo:flow>
      </fo:page-sequence>
  </fo:root>
  </xsl:template>
  
  
  <xsl:template match="billheadItem">
    <fo:table-row height="6mm">
      <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block text-align="center" font-family="mysimsun">
          <xsl:value-of select="id" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="tourNo" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="description" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block text-align="center" font-family="mysimsun">
          <xsl:value-of select="currency" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block text-align="right" font-family="mysimsun">
          <xsl:value-of select="roe" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block text-align="right" font-family="mysimsun">
          <xsl:value-of select="fcNowpayPayment" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block text-align="right" font-family="mysimsun">
          <xsl:value-of select="nowpayPayment" />
        </fo:block>
      </fo:table-cell>
    </fo:table-row>
  </xsl:template>
  
</xsl:stylesheet>
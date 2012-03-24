<?xml version="1.0" encoding="UTF-8"?>
<!-- $Id: WholeTourReckoningPrint.xsl,v 1.1 2009/03/01 16:24:15 zhangst Exp $ -->
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">
  <xsl:output method="xml" version="1.0" omit-xml-declaration="no" indent="yes" />
  <xsl:param name="versionParam" select="'1.0'" />

  <!-- ========================= -->
  <!--         团队帐单打印         -->
  <!-- ========================= -->
  <xsl:template match="reckoning">
    <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format" language="zh">
      <fo:layout-master-set>
        <fo:simple-page-master master-name="simpleA4" page-height="29.7cm" page-width="21cm" margin-top="2cm" margin-bottom="2cm" margin-left="2cm" margin-right="2cm">
          <fo:region-body />
        </fo:simple-page-master>
      </fo:layout-master-set>
      <fo:page-sequence master-reference="simpleA4">
        <fo:flow flow-name="xsl-region-body">
          <fo:block font-size="15pt" font-weight="bold" text-align="center" font-family="mysimkai" space-before="5mm" space-after="10mm">团费结算单</fo:block>
          <fo:block font-size="9pt">
            <fo:table table-layout="fixed" width="100%">
              <fo:table-column column-width="10%" />
              <fo:table-column column-width="40%" />
              <fo:table-column column-width="10%" />
              <fo:table-column column-width="40%" />

              <fo:table-header>
                <fo:table-row height="6mm">
                  <fo:table-cell number-columns-spanned="2">
                    <fo:block font-family="mysimsun">编号：<xsl:value-of select="reckoningId" />-<xsl:value-of select="version" /></fo:block>
                  </fo:table-cell>
                  <fo:table-cell number-columns-spanned="2">
                    <fo:block text-align="right" font-family="mysimsun">制作日期：<xsl:value-of select="createDate" /></fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-header>
              <fo:table-body>
                <xsl:call-template name="reckoningHeader" />
              </fo:table-body>
            </fo:table>

          </fo:block>
          <fo:block font-size="9pt" font-family="mysimsun" space-after="3mm" space-before="5mm">贵部委托我部操作的旅游团队,前期工作现已操作完毕.具体如下：</fo:block>

          <fo:block font-size="9pt">
            <fo:table table-layout="fixed" width="100%">
              <fo:table-column column-width="4%" />
              <fo:table-column column-width="56%" />
              <fo:table-column column-width="15%" />
              <fo:table-column column-width="5%" />
              <fo:table-column column-width="5%" />
              <fo:table-column column-width="15%" />

              <fo:table-header>
                <fo:table-row height="6mm" background-color="#DDDDDD">
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-weight="bold" font-family="mysimsun">序号</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-weight="bold" font-family="mysimsun">摘要</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-weight="bold" font-family="mysimsun">单价</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-weight="bold" font-family="mysimsun">人数</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-weight="bold" font-family="mysimsun">单位</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-weight="bold" font-family="mysimsun">金额</fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-header>

              <fo:table-body>
                <xsl:apply-templates select="reckoningAcct" />
              </fo:table-body>

              <fo:table-footer>
                <fo:table-row height="6mm">
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block font-family="mysimsun">
                      
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell number-columns-spanned="4" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-family="mysimsun">合计</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="right" font-family="mysimsun">
                      <xsl:value-of select="allAmount" />
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-footer>
            </fo:table>
          </fo:block>
          
          <fo:block font-family="mysimsun" space-before="10mm" space-after="3mm">请将上述款项汇至我公司如下帐号：</fo:block>
          <fo:block font-family="mysimsun">户　名：旅行社</fo:block>
          <fo:block font-family="mysimsun">开户行：XXXX银行XXXX支行</fo:block>
          <fo:block font-family="mysimsun">帐　号：XXXXXX-XXXXXXXX</fo:block>
       
       <!--     
          <fo:block font-family="mysimsun" space-before="10mm" space-after="3mm">帐单状态：</fo:block>
          <fo:block font-size="9pt" space-before="5mm">
             <fo:table table-layout="fixed" width="100%">
               <fo:table-column column-width="15%"/>
               <fo:table-column column-width="35%"/>
               <fo:table-column column-width="15%"/>
               <fo:table-column column-width="35%"/>
               <fo:table-body>
                  <fo:table-row height="6mm">
                     <fo:table-cell>
                        <fo:block font-family="mysimsun">创建人：</fo:block>
                     </fo:table-cell>
                     <fo:table-cell>
                        <fo:block font-family="mysimsun"><xsl:value-of select="createdby" /></fo:block>
                     </fo:table-cell>
                     <fo:table-cell>
                        <fo:block font-family="mysimsun">创建日期：</fo:block>
                     </fo:table-cell>
                     <fo:table-cell>
                        <fo:block font-family="mysimsun"><xsl:value-of select="createDate" /></fo:block>
                     </fo:table-cell>
                  </fo:table-row>
                  <fo:table-row height="6mm">
                     <fo:table-cell>
                        <fo:block font-family="mysimsun">打印次数：</fo:block>
                     </fo:table-cell>
                     <fo:table-cell>
                        <fo:block font-family="mysimsun"><xsl:value-of select="printedCount" /></fo:block>
                     </fo:table-cell>
                     <fo:table-cell>
                        <fo:block font-family="mysimsun">打印日期：</fo:block>
                     </fo:table-cell>
                     <fo:table-cell>
                        <fo:block font-family="mysimsun"><xsl:value-of select="printDate" /></fo:block>
                     </fo:table-cell>
                  </fo:table-row>
               </fo:table-body>
             </fo:table>
             
          </fo:block>
        -->

        </fo:flow>
      </fo:page-sequence>
    </fo:root>
  </xsl:template>



  <xsl:template name="reckoningHeader">
    <fo:table-row height="6mm">
      <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block text-align="center" font-family="mysimsun" font-weight="bold">客户</fo:block>
      </fo:table-cell>
      <fo:table-cell number-columns-spanned="3" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="agent" />
        </fo:block>
      </fo:table-cell>
    </fo:table-row>

    <fo:table-row height="6mm">
      <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block text-align="center" font-family="mysimsun" font-weight="bold">联系人</fo:block>
      </fo:table-cell>
      <fo:table-cell number-columns-spanned="3" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="contact" />
        </fo:block>
      </fo:table-cell>
    </fo:table-row>

    <fo:table-row height="6mm">
      <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block text-align="center" font-family="mysimsun" font-weight="bold">电话</fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="phone" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block text-align="center" font-family="mysimsun" font-weight="bold">传真</fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="fax" />
        </fo:block>
      </fo:table-cell>
    </fo:table-row>

    <fo:table-row height="6mm">
      <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block text-align="center" font-family="mysimsun" font-weight="bold">线路</fo:block>
      </fo:table-cell>
      <fo:table-cell number-columns-spanned="3" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="routeName" />
        </fo:block>
      </fo:table-cell>
    </fo:table-row>

    <fo:table-row height="6mm">
      <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block text-align="center" font-family="mysimsun" font-weight="bold">团号</fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="tourNo" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block text-align="center" font-family="mysimsun" font-weight="bold">出团时间</fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="outDate" />
        </fo:block>
      </fo:table-cell>
    </fo:table-row>

    <fo:table-row height="6mm">
      <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block text-align="center" font-family="mysimsun" font-weight="bold">人数</fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="pax" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block text-align="center" font-family="mysimsun" font-weight="bold">领队数</fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="leaderPax" />
        </fo:block>
      </fo:table-cell>
    </fo:table-row>
  </xsl:template>


  <xsl:template match="reckoningAcct">
    <fo:table-row height="6mm">
      <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block text-align="center" font-family="mysimsun">
          <xsl:value-of select="itemId" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="description" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block text-align="right" font-family="mysimsun">
          <xsl:value-of select="unitPrice" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block text-align="right" font-family="mysimsun">
          <xsl:value-of select="count" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block text-align="center" font-family="mysimsun">
          <xsl:value-of select="unit" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block text-align="right" font-family="mysimsun">
          <xsl:value-of select="amount" />
        </fo:block>
      </fo:table-cell>
    </fo:table-row>
  </xsl:template>



</xsl:stylesheet>
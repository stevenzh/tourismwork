<?xml version="1.0" encoding="UTF-8"?>
<!-- $Id: TourBalancePrint.xsl,v 1.1 2009/03/01 16:24:15 zhangst Exp $ -->
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">
  
  <xsl:output method="xml" version="1.0" omit-xml-declaration="no" indent="yes" />
  <xsl:param name="versionParam" select="'1.0'" />

  <!-- ========================= -->
  <!--         单团核算表打印        -->
  <!-- ========================= -->
  <xsl:template match="singleTourBalance">
    <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format" language="zh">
      <fo:layout-master-set>
        <fo:simple-page-master master-name="simpleA4" page-height="29.7cm" page-width="21cm" margin-top="2cm" margin-bottom="2cm" margin-left="2cm" margin-right="2cm">
          <fo:region-body />
        </fo:simple-page-master>
      </fo:layout-master-set>
      <fo:page-sequence master-reference="simpleA4">
        <fo:flow flow-name="xsl-region-body">
          <fo:block font-size="16pt" font-family="mysimkai" font-weight="bold" text-align="center" space-after="3mm">
              <xsl:value-of select="tourNo"/>团队结算帐单
          </fo:block>
          <fo:block font-size="10pt" font-weight="bold" linefeed-treatment="preserve" white-space-treatment="preserve" font-family="mysimhei">
             <fo:table table-layout="fixed" width="100%">
              <fo:table-column column-width="50%" />
              <fo:table-column column-width="50%" />
              
               <fo:table-body>
                <fo:table-row height="6mm">
                  <fo:table-cell>
                    <fo:block> 
                        操作部门：<xsl:value-of select="department"/>
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell>
                    <fo:block text-align="right">
                         结算帐单号：<xsl:value-of select="accountId" />-<xsl:value-of select="version" />
                    </fo:block>
                  </fo:table-cell>
                 </fo:table-row>
               </fo:table-body>
              </fo:table>
          </fo:block>
          
          
          <fo:block font-size="10pt" font-family="mysimsun">
            <fo:table table-layout="fixed" width="100%">
              <fo:table-column column-width="12%" />
              <fo:table-column column-width="21%" />
              <fo:table-column column-width="12%" />
              <fo:table-column column-width="21%" />
              <fo:table-column column-width="12%" />
              <fo:table-column column-width="22%" />
              
              <fo:table-body>
                <fo:table-row height="6mm">
                  <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>线　　路</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" number-columns-spanned="5" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                       <xsl:value-of select="routeName" />
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                
                <fo:table-row height="6mm">
                  <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>团　　号</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" number-columns-spanned="5" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                       <xsl:value-of select="tourNo" />
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                
                <fo:table-row height="6mm">
                  <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>出发日期</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                       <xsl:value-of select="outDate" />
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>天　　数</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" number-columns-spanned="3" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                       <xsl:value-of select="datePax" />
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                
                <fo:table-row height="6mm">
                  <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>客人人数</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                       <xsl:value-of select="allPax" />
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>领 队 数</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                       <xsl:value-of select="leaderPax" />
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>领队名单</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                       <xsl:value-of select="leaderName" />
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                
                <fo:table-row height="6mm">
                  <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>房间情况</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" number-columns-spanned="5" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                       双人间：<xsl:value-of select="doubleRoom" />   单人间：<xsl:value-of select="singleRoom" />   加床间：<xsl:value-of select="extraBedRoom" />
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                
                <fo:table-row height="6mm">
                  <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>应 收 款</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                       <xsl:value-of select="muAmount" />
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>已 收 款</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                       <xsl:value-of select="alAmount" />
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>未 收 款</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                       <xsl:value-of select="wiAmount" />
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                
                <fo:table-row height="6mm">
                  <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>备　　注</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" number-columns-spanned="5" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                       <xsl:value-of select="remark" />
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                
              </fo:table-body>
            </fo:table>
          </fo:block>
          
          <fo:block font-size="10pt" font-family="mysimhei" font-weight="bold" space-before="2mm">一、结算明细表</fo:block>
          
          <fo:block font-size="10pt" font-family="mysimsun">
            <fo:table table-layout="fixed" width="100%">
              <fo:table-column column-width="12%" />
              <fo:table-column column-width="21%" />
              <fo:table-column column-width="12%" />
              <fo:table-column column-width="21%" />
              <fo:table-column column-width="12%" />
              <fo:table-column column-width="22%" />
              
              <fo:table-body>
                <fo:table-row height="6mm">
                  <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>减免人数</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                       <xsl:value-of select="exemptPax" />
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>儿童人数</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                       <xsl:value-of select="childPax" />
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>结算人数</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                       <xsl:value-of select="pax" />
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                
                <fo:table-row height="6mm">
                  <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>团队总收入</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                       <xsl:value-of select="amount" />
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>纯 团 费</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                       <xsl:value-of select="tourAmount" />
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>成本合计</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                       <xsl:value-of select="costAmount" />
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                
                <fo:table-row height="6mm">
                  <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>毛　　利</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                       <xsl:value-of select="grossAmount" />
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>毛 利 率</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                       <xsl:value-of select="grossAmountRate" />%
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>人均毛利</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                       <xsl:value-of select="grossAmountAverage" />
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                
                <fo:table-row height="6mm">
                  <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>其它收入</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                       <xsl:value-of select="extrIncome" />
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>收入说明</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" number-columns-spanned="3" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                       <xsl:value-of select="extrIncomeDec" />
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                
             </fo:table-body>
            </fo:table>
           </fo:block>
           
           <fo:block font-size="10pt" font-family="mysimhei" font-weight="bold" space-before="2mm">二、营业收入</fo:block>
           
           <fo:block font-size="10pt">
            <fo:table table-layout="fixed" width="100%">
              <fo:table-column column-width="4%" />
              <fo:table-column column-width="36%" />
              <fo:table-column column-width="12%" />
              <fo:table-column column-width="12%" />
              <fo:table-column column-width="12%" />
              <fo:table-column column-width="12%" />
              <fo:table-column column-width="12%" />
              
              <fo:table-header>
                <fo:table-row height="6mm" background-color="#DDDDDD">
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-weight="bold" font-family="mysimsun">NO.</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-weight="bold" font-family="mysimsun">客户</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-weight="bold" font-family="mysimsun">销售员</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-weight="bold" font-family="mysimsun">预定人数</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-weight="bold" font-family="mysimsun">应收款</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-weight="bold" font-family="mysimsun">已收款</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-weight="bold" font-family="mysimsun">未收款</fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-header>

              <fo:table-body>
                <xsl:apply-templates select="book" />
              </fo:table-body>
              
              <fo:table-footer>
                <fo:table-row height="6mm">
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block font-family="mysimsun">
                      
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell number-columns-spanned="2" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-family="mysimsun">合计</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="right" font-family="mysimsun">
                      <xsl:value-of select="totalPax" />
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="right" font-family="mysimsun">
                      <xsl:value-of select="muAmount" />
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="right" font-family="mysimsun">
                      <xsl:value-of select="alAmount" />
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="right" font-family="mysimsun">
                      <xsl:value-of select="wiAmount" />
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
              </fo:table-footer>
              
            </fo:table>
          </fo:block>
           
           
           <fo:block font-size="10pt" font-family="mysimhei" font-weight="bold" space-before="2mm">三、营业成本</fo:block>
           
           <fo:block font-size="10pt">
            <fo:table table-layout="fixed" width="100%">
              <fo:table-column column-width="4%" />
              <fo:table-column column-width="10%" />
              <fo:table-column column-width="28%" />
              <fo:table-column column-width="16%" />
              <fo:table-column column-width="5%" />
              <fo:table-column column-width="5%" />
              <fo:table-column column-width="11%" />
              <fo:table-column column-width="5%" />
              <fo:table-column column-width="5%" />
              <fo:table-column column-width="11%" />
              
              <fo:table-header>
                <fo:table-row height="6mm" background-color="#DDDDDD">
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-weight="bold" font-family="mysimsun">NO.</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-weight="bold" font-family="mysimsun">费用类型</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-weight="bold" font-family="mysimsun">应付款单位</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-weight="bold" font-family="mysimsun">摘要</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-weight="bold" font-family="mysimsun">币种</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-weight="bold" font-family="mysimsun">汇率</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-weight="bold" font-family="mysimsun">单价(外币)</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center" font-weight="bold" font-family="mysimsun">数量</fo:block>
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
                <xsl:apply-templates select="singleTourCostAcct" />
              </fo:table-body>
             </fo:table>
           </fo:block>
           
           <fo:block space-before="5mm"> </fo:block>
           
           
            <fo:block font-size="10pt" font-family="mysimsun">
            <fo:table table-layout="fixed" width="100%">
              <fo:table-column column-width="8%" />
              <fo:table-column column-width="12%" />
              <fo:table-column column-width="10%" />
              <fo:table-column column-width="20%" />
              <fo:table-column column-width="8%" />
              <fo:table-column column-width="12%" />
              <fo:table-column column-width="10%" />
              <fo:table-column column-width="20%" />
              
              <fo:table-body>
                <fo:table-row height="6mm">
                  <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>操作人</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                       <xsl:value-of select="oprateUser" />
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>建团日期</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center">
                       <xsl:value-of select="CTourDate" />
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>制单人</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                       <xsl:value-of select="opUserName" />
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>制单日期</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center">
                       <xsl:value-of select="opDate" />
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                <fo:table-row height="6mm">
                  <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>审核人</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                       <xsl:value-of select="frUserName" />
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell background-color="#DDDDDD" display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>审核日期</fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block text-align="center">
                       <xsl:value-of select="frDate" />
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                    
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                    
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                    
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
                    <fo:block>
                    
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
  
  
  <xsl:template match="book">
    <fo:table-row height="6mm">
      <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block text-align="center" font-family="mysimsun">
          <xsl:value-of select="id" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block text-align="center" font-family="mysimsun">
          <xsl:value-of select="customerName" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block text-align="center" font-family="mysimsun">
          <xsl:value-of select="seller" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block text-align="right" font-family="mysimsun">
          <xsl:value-of select="pax" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block text-align="right" font-family="mysimsun">
          <xsl:value-of select="expenseFinalExpense" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block text-align="right" font-family="mysimsun">
          <xsl:value-of select="payCosts" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block text-align="right" font-family="mysimsun">
          <xsl:value-of select="unPay" />
        </fo:block>
      </fo:table-cell>
    </fo:table-row>
  </xsl:template>
  
  
  
  <xsl:template match="singleTourCostAcct">
    <fo:table-row height="6mm">
      <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block text-align="center" font-family="mysimsun">
          <xsl:value-of select="id" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="amountTypeName" />
        </fo:block>
      </fo:table-cell>
      <fo:table-cell display-align="center" border-color="black" border-style="solid" border-width=".5pt">
        <fo:block font-family="mysimsun">
          <xsl:value-of select="supplier" />
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
<%
/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ include file="/html/portlet/admin/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

String userTrackerId = ParamUtil.getString(request, "userTrackerId");

Map liveUsers = LiveUsers.getSessionUsers();

UserTracker userTracker = (UserTracker)liveUsers.get(userTrackerId);
%>

<form action="<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="struts_action" value="/admin/edit_session" /></portlet:actionURL>" method="post" name="<portlet:namespace />fm">
<input name="<portlet:namespace />redirect" type="hidden" value="<%= redirect %>" />
<input name="<portlet:namespace />userTrackerId" type="hidden" value="<%= userTrackerId %>" />

<liferay-ui:tabs names="session" />

<c:choose>
	<c:when test="<%= userTracker == null %>">
		<bean:message key="session-id-not-found" />

		<br /><br />

		<input type="button" value="<bean:message key="cancel" />" onClick="self.location = '<%= redirect %>';" />
	</c:when>
	<c:otherwise>

		<%
		User user2 = null;

		try {
			user2 = UserLocalServiceUtil.getUserById(userTracker.getUserId());
		}
		catch (NoSuchUserException nsue) {
		}
		%>

		<table class="liferay-table">
		<tr>
			<td>
				<bean:message key="session-id" />:
			</td>
			<td>
				<%= userTrackerId %>
			</td>
		</tr>
		<tr>
			<td>
				<bean:message key="user-id" />:
			</td>
			<td>
				<%= userTracker.getUserId() %>
			</td>
		</tr>
		<tr>
			<td>
				<bean:message key="name" />:
			</td>
			<td>
				<%= (user2 != null) ? user2.getFullName() : LanguageUtil.get(pageContext, "not-available") %>
			</td>
		</tr>
		<tr>
			<td>
				<bean:message key="email-address" />:
			</td>
			<td>
				<%= (user2 != null) ? user2.getEmailAddress() : LanguageUtil.get(pageContext, "not-available") %>
			</td>
		</tr>
		<tr>
			<td>
				<bean:message key="last-request" />:
			</td>
			<td>
				<%= dateFormatDateTime.format(userTracker.getModifiedDate()) %>
			</td>
		</tr>
		<tr>
			<td>
				<bean:message key="num-of-hits" />:
			</td>
			<td>
				<%= userTracker.getHits() %>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<br />
			</td>
		</tr>
		<tr>
			<td>
				<bean:message key="browser-os-type" />:
			</td>
			<td>
				<%= userTracker.getUserAgent() %>
			</td>
		</tr>
		<tr>
			<td>
				<bean:message key="remote-host-ip" />:
			</td>
			<td>
				<%= userTracker.getRemoteAddr() %> / <%= userTracker.getRemoteHost() %>
			</td>
		</tr>
		</table>

		<br />

		<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
				<table border="0" cellpadding="4" cellspacing="0" width="100%">
				<tr>
					<td class="beta-gradient">
						<b><bean:message key="accessed-urls" /></b>
					</td>
					<td align="right" class="beta-gradient">
						<span style="font-size: xx-small;">
						[<a href="javascript: void(0);" onClick="Liferay.Util.toggleByIdSpan(this, '<portlet:namespace />accessedUrls'); self.focus();"><span><bean:message key="show" /></span><span style="display: none;"><bean:message key="hide" /></span></a>]
						</span>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<div id="<portlet:namespace />accessedUrls" style="display: none;">
					<table border="0" cellpadding="4" cellspacing="0" width="100%">

					<%
					List paths = userTracker.getPaths();

					for (int i = 0; i < paths.size(); i++) {
						UserTrackerPath userTrackerPath = (UserTrackerPath)paths.get(i);

						String className = "portlet-section-body";
						String classHoverName = "portlet-section-body-hover";

						if (MathUtil.isEven(i)) {
							className = "portlet-section-alternate";
							classHoverName = "portlet-section-alternate-hover";
						}
					%>

						<tr class="<%= className %>" style="font-size: xx-small;" onMouseEnter="this.className = '<%= classHoverName %>';" onMouseLeave="this.className = '<%= className %>';">
							<td valign="top">
								<%= StringUtil.replace(userTrackerPath.getPath(), "&", "& ") %>
							</td>
							<td nowrap valign="top">
								<%= dateFormatDateTime.format(userTrackerPath.getPathDate()) %>
							</td>
						</tr>

					<%
					}
					%>

					</table>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<table border="0" cellpadding="4" cellspacing="0" width="100%">
				<tr>
					<td class="beta-gradient">
						<b><bean:message key="session-attributes" /></b>
					</td>
					<td align="right" class="beta-gradient">
						<span style="font-size: xx-small;">
						[<a href="javascript: void(0);" onClick="Liferay.Util.toggleByIdSpan(this, '<portlet:namespace />sessionAttributes'); self.focus();"><span><bean:message key="show" /></span><span style="display: none;"><bean:message key="hide" /></span></a>]
						</span>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<div id="<portlet:namespace />sessionAttributes" style="display: none;">
					<table border="0" cellpadding="4" cellspacing="0" width="100%">

					<%
					boolean userSessionAlive = true;

					HttpSession userSession = PortalSessionContext.get(userTrackerId);

					if (userSession != null) {
						try {
							int counter = 0;

							Set sortedAttrNames = new TreeSet();

							Enumeration enu = userSession.getAttributeNames();

							while (enu.hasMoreElements()) {
								String attrName = (String)enu.nextElement();

								sortedAttrNames.add(attrName);
							}

							Iterator itr = sortedAttrNames.iterator();

							while (itr.hasNext()) {
								String attrName = (String)itr.next();

								String className = "portlet-section-body";
								String classHoverName = "portlet-section-body-hover";

								if (MathUtil.isEven(counter++)) {
									className = "portlet-section-alternate";
									classHoverName = "portlet-section-alternate-hover";
								}
					%>

								<tr class="<%= className %>" style="font-size: xx-small;" onMouseEnter="this.className = '<%= classHoverName %>';" onMouseLeave="this.className = '<%= className %>';">
									<td valign="top">
										<%= attrName %>
									</td>
								</tr>

					<%
							}
						}
						catch (Exception e) {
							userSessionAlive = false;

							e.printStackTrace();
						}
					}
					%>

					</table>
				</div>
			</td>
		</tr>
		</table>

		<br />

		<c:if test="<%= userSessionAlive && !session.getId().equals(userTrackerId) %>">
			<input type="submit" value="<bean:message key="kill-session" />" />
		</c:if>

		<input type="button" value="<bean:message key="cancel" />" onClick="self.location = '<%= redirect %>';" />
	</c:otherwise>
</c:choose>

</form>
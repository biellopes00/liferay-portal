<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
AssetRendererFactory<?> assetRendererFactory = (AssetRendererFactory<?>)request.getAttribute(WebKeys.ASSET_RENDERER_FACTORY);

JournalArticle article = (JournalArticle)request.getAttribute(WebKeys.JOURNAL_ARTICLE);
%>

<liferay-journal:journal-article-display
	articleDisplay="<%= (JournalArticleDisplay)request.getAttribute(WebKeys.JOURNAL_ARTICLE_DISPLAY) %>"
	paginationURL='<%=
		PortletURLBuilder.createRenderURL(
			renderResponse
		).setMVCPath(
			"/view_content.jsp"
		).setParameter(
			"classNameId", assetRendererFactory.getClassNameId()
		).setParameter(
			"classPK", JournalArticleAssetRenderer.getClassPK(article)
		).buildPortletURL()
	%>'
/>
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.bookmarks.service.base;

import com.liferay.counter.service.CounterLocalService;
import com.liferay.counter.service.CounterService;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.ResourceFinder;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserFinder;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.portlet.asset.service.AssetEntryLocalService;
import com.liferay.portlet.asset.service.AssetEntryService;
import com.liferay.portlet.asset.service.AssetTagLocalService;
import com.liferay.portlet.asset.service.AssetTagService;
import com.liferay.portlet.asset.service.persistence.AssetEntryFinder;
import com.liferay.portlet.asset.service.persistence.AssetEntryPersistence;
import com.liferay.portlet.asset.service.persistence.AssetTagFinder;
import com.liferay.portlet.asset.service.persistence.AssetTagPersistence;
import com.liferay.portlet.bookmarks.model.BookmarksEntry;
import com.liferay.portlet.bookmarks.service.BookmarksEntryLocalService;
import com.liferay.portlet.bookmarks.service.BookmarksEntryService;
import com.liferay.portlet.bookmarks.service.BookmarksFolderLocalService;
import com.liferay.portlet.bookmarks.service.BookmarksFolderService;
import com.liferay.portlet.bookmarks.service.persistence.BookmarksEntryFinder;
import com.liferay.portlet.bookmarks.service.persistence.BookmarksEntryPersistence;
import com.liferay.portlet.bookmarks.service.persistence.BookmarksFolderPersistence;
import com.liferay.portlet.expando.service.ExpandoValueLocalService;
import com.liferay.portlet.expando.service.ExpandoValueService;
import com.liferay.portlet.expando.service.persistence.ExpandoValuePersistence;

import java.util.List;

/**
 * <a href="BookmarksEntryLocalServiceBaseImpl.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Brian Wing Shun Chan
 */
public abstract class BookmarksEntryLocalServiceBaseImpl
	implements BookmarksEntryLocalService {
	public BookmarksEntry addBookmarksEntry(BookmarksEntry bookmarksEntry)
		throws SystemException {
		bookmarksEntry.setNew(true);

		return bookmarksEntryPersistence.update(bookmarksEntry, false);
	}

	public BookmarksEntry createBookmarksEntry(long entryId) {
		return bookmarksEntryPersistence.create(entryId);
	}

	public void deleteBookmarksEntry(long entryId)
		throws PortalException, SystemException {
		bookmarksEntryPersistence.remove(entryId);
	}

	public void deleteBookmarksEntry(BookmarksEntry bookmarksEntry)
		throws SystemException {
		bookmarksEntryPersistence.remove(bookmarksEntry);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return bookmarksEntryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return bookmarksEntryPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	public BookmarksEntry getBookmarksEntry(long entryId)
		throws PortalException, SystemException {
		return bookmarksEntryPersistence.findByPrimaryKey(entryId);
	}

	public List<BookmarksEntry> getBookmarksEntries(int start, int end)
		throws SystemException {
		return bookmarksEntryPersistence.findAll(start, end);
	}

	public int getBookmarksEntriesCount() throws SystemException {
		return bookmarksEntryPersistence.countAll();
	}

	public BookmarksEntry updateBookmarksEntry(BookmarksEntry bookmarksEntry)
		throws SystemException {
		bookmarksEntry.setNew(false);

		return bookmarksEntryPersistence.update(bookmarksEntry, true);
	}

	public BookmarksEntry updateBookmarksEntry(BookmarksEntry bookmarksEntry,
		boolean merge) throws SystemException {
		bookmarksEntry.setNew(false);

		return bookmarksEntryPersistence.update(bookmarksEntry, merge);
	}

	public BookmarksEntryLocalService getBookmarksEntryLocalService() {
		return bookmarksEntryLocalService;
	}

	public void setBookmarksEntryLocalService(
		BookmarksEntryLocalService bookmarksEntryLocalService) {
		this.bookmarksEntryLocalService = bookmarksEntryLocalService;
	}

	public BookmarksEntryService getBookmarksEntryService() {
		return bookmarksEntryService;
	}

	public void setBookmarksEntryService(
		BookmarksEntryService bookmarksEntryService) {
		this.bookmarksEntryService = bookmarksEntryService;
	}

	public BookmarksEntryPersistence getBookmarksEntryPersistence() {
		return bookmarksEntryPersistence;
	}

	public void setBookmarksEntryPersistence(
		BookmarksEntryPersistence bookmarksEntryPersistence) {
		this.bookmarksEntryPersistence = bookmarksEntryPersistence;
	}

	public BookmarksEntryFinder getBookmarksEntryFinder() {
		return bookmarksEntryFinder;
	}

	public void setBookmarksEntryFinder(
		BookmarksEntryFinder bookmarksEntryFinder) {
		this.bookmarksEntryFinder = bookmarksEntryFinder;
	}

	public BookmarksFolderLocalService getBookmarksFolderLocalService() {
		return bookmarksFolderLocalService;
	}

	public void setBookmarksFolderLocalService(
		BookmarksFolderLocalService bookmarksFolderLocalService) {
		this.bookmarksFolderLocalService = bookmarksFolderLocalService;
	}

	public BookmarksFolderService getBookmarksFolderService() {
		return bookmarksFolderService;
	}

	public void setBookmarksFolderService(
		BookmarksFolderService bookmarksFolderService) {
		this.bookmarksFolderService = bookmarksFolderService;
	}

	public BookmarksFolderPersistence getBookmarksFolderPersistence() {
		return bookmarksFolderPersistence;
	}

	public void setBookmarksFolderPersistence(
		BookmarksFolderPersistence bookmarksFolderPersistence) {
		this.bookmarksFolderPersistence = bookmarksFolderPersistence;
	}

	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	public CounterService getCounterService() {
		return counterService;
	}

	public void setCounterService(CounterService counterService) {
		this.counterService = counterService;
	}

	public ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	public void setResourceLocalService(
		ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	public ResourceService getResourceService() {
		return resourceService;
	}

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public ResourcePersistence getResourcePersistence() {
		return resourcePersistence;
	}

	public void setResourcePersistence(ResourcePersistence resourcePersistence) {
		this.resourcePersistence = resourcePersistence;
	}

	public ResourceFinder getResourceFinder() {
		return resourceFinder;
	}

	public void setResourceFinder(ResourceFinder resourceFinder) {
		this.resourceFinder = resourceFinder;
	}

	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public UserFinder getUserFinder() {
		return userFinder;
	}

	public void setUserFinder(UserFinder userFinder) {
		this.userFinder = userFinder;
	}

	public AssetEntryLocalService getAssetEntryLocalService() {
		return assetEntryLocalService;
	}

	public void setAssetEntryLocalService(
		AssetEntryLocalService assetEntryLocalService) {
		this.assetEntryLocalService = assetEntryLocalService;
	}

	public AssetEntryService getAssetEntryService() {
		return assetEntryService;
	}

	public void setAssetEntryService(AssetEntryService assetEntryService) {
		this.assetEntryService = assetEntryService;
	}

	public AssetEntryPersistence getAssetEntryPersistence() {
		return assetEntryPersistence;
	}

	public void setAssetEntryPersistence(
		AssetEntryPersistence assetEntryPersistence) {
		this.assetEntryPersistence = assetEntryPersistence;
	}

	public AssetEntryFinder getAssetEntryFinder() {
		return assetEntryFinder;
	}

	public void setAssetEntryFinder(AssetEntryFinder assetEntryFinder) {
		this.assetEntryFinder = assetEntryFinder;
	}

	public AssetTagLocalService getAssetTagLocalService() {
		return assetTagLocalService;
	}

	public void setAssetTagLocalService(
		AssetTagLocalService assetTagLocalService) {
		this.assetTagLocalService = assetTagLocalService;
	}

	public AssetTagService getAssetTagService() {
		return assetTagService;
	}

	public void setAssetTagService(AssetTagService assetTagService) {
		this.assetTagService = assetTagService;
	}

	public AssetTagPersistence getAssetTagPersistence() {
		return assetTagPersistence;
	}

	public void setAssetTagPersistence(AssetTagPersistence assetTagPersistence) {
		this.assetTagPersistence = assetTagPersistence;
	}

	public AssetTagFinder getAssetTagFinder() {
		return assetTagFinder;
	}

	public void setAssetTagFinder(AssetTagFinder assetTagFinder) {
		this.assetTagFinder = assetTagFinder;
	}

	public ExpandoValueLocalService getExpandoValueLocalService() {
		return expandoValueLocalService;
	}

	public void setExpandoValueLocalService(
		ExpandoValueLocalService expandoValueLocalService) {
		this.expandoValueLocalService = expandoValueLocalService;
	}

	public ExpandoValueService getExpandoValueService() {
		return expandoValueService;
	}

	public void setExpandoValueService(ExpandoValueService expandoValueService) {
		this.expandoValueService = expandoValueService;
	}

	public ExpandoValuePersistence getExpandoValuePersistence() {
		return expandoValuePersistence;
	}

	public void setExpandoValuePersistence(
		ExpandoValuePersistence expandoValuePersistence) {
		this.expandoValuePersistence = expandoValuePersistence;
	}

	protected void runSQL(String sql) throws SystemException {
		try {
			DB db = DBFactoryUtil.getDB();

			db.runSQL(sql);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(name = "com.liferay.portlet.bookmarks.service.BookmarksEntryLocalService.impl")
	protected BookmarksEntryLocalService bookmarksEntryLocalService;
	@BeanReference(name = "com.liferay.portlet.bookmarks.service.BookmarksEntryService.impl")
	protected BookmarksEntryService bookmarksEntryService;
	@BeanReference(name = "com.liferay.portlet.bookmarks.service.persistence.BookmarksEntryPersistence.impl")
	protected BookmarksEntryPersistence bookmarksEntryPersistence;
	@BeanReference(name = "com.liferay.portlet.bookmarks.service.persistence.BookmarksEntryFinder.impl")
	protected BookmarksEntryFinder bookmarksEntryFinder;
	@BeanReference(name = "com.liferay.portlet.bookmarks.service.BookmarksFolderLocalService.impl")
	protected BookmarksFolderLocalService bookmarksFolderLocalService;
	@BeanReference(name = "com.liferay.portlet.bookmarks.service.BookmarksFolderService.impl")
	protected BookmarksFolderService bookmarksFolderService;
	@BeanReference(name = "com.liferay.portlet.bookmarks.service.persistence.BookmarksFolderPersistence.impl")
	protected BookmarksFolderPersistence bookmarksFolderPersistence;
	@BeanReference(name = "com.liferay.counter.service.CounterLocalService.impl")
	protected CounterLocalService counterLocalService;
	@BeanReference(name = "com.liferay.counter.service.CounterService.impl")
	protected CounterService counterService;
	@BeanReference(name = "com.liferay.portal.service.ResourceLocalService.impl")
	protected ResourceLocalService resourceLocalService;
	@BeanReference(name = "com.liferay.portal.service.ResourceService.impl")
	protected ResourceService resourceService;
	@BeanReference(name = "com.liferay.portal.service.persistence.ResourcePersistence.impl")
	protected ResourcePersistence resourcePersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.ResourceFinder.impl")
	protected ResourceFinder resourceFinder;
	@BeanReference(name = "com.liferay.portal.service.UserLocalService.impl")
	protected UserLocalService userLocalService;
	@BeanReference(name = "com.liferay.portal.service.UserService.impl")
	protected UserService userService;
	@BeanReference(name = "com.liferay.portal.service.persistence.UserPersistence.impl")
	protected UserPersistence userPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.UserFinder.impl")
	protected UserFinder userFinder;
	@BeanReference(name = "com.liferay.portlet.asset.service.AssetEntryLocalService.impl")
	protected AssetEntryLocalService assetEntryLocalService;
	@BeanReference(name = "com.liferay.portlet.asset.service.AssetEntryService.impl")
	protected AssetEntryService assetEntryService;
	@BeanReference(name = "com.liferay.portlet.asset.service.persistence.AssetEntryPersistence.impl")
	protected AssetEntryPersistence assetEntryPersistence;
	@BeanReference(name = "com.liferay.portlet.asset.service.persistence.AssetEntryFinder.impl")
	protected AssetEntryFinder assetEntryFinder;
	@BeanReference(name = "com.liferay.portlet.asset.service.AssetTagLocalService.impl")
	protected AssetTagLocalService assetTagLocalService;
	@BeanReference(name = "com.liferay.portlet.asset.service.AssetTagService.impl")
	protected AssetTagService assetTagService;
	@BeanReference(name = "com.liferay.portlet.asset.service.persistence.AssetTagPersistence.impl")
	protected AssetTagPersistence assetTagPersistence;
	@BeanReference(name = "com.liferay.portlet.asset.service.persistence.AssetTagFinder.impl")
	protected AssetTagFinder assetTagFinder;
	@BeanReference(name = "com.liferay.portlet.expando.service.ExpandoValueLocalService.impl")
	protected ExpandoValueLocalService expandoValueLocalService;
	@BeanReference(name = "com.liferay.portlet.expando.service.ExpandoValueService.impl")
	protected ExpandoValueService expandoValueService;
	@BeanReference(name = "com.liferay.portlet.expando.service.persistence.ExpandoValuePersistence.impl")
	protected ExpandoValuePersistence expandoValuePersistence;
}
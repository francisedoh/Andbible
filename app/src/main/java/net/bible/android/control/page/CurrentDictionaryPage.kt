/*
 * Copyright (c) 2018 Martin Denham, Tuomas Airaksinen and the And Bible contributors.
 *
 * This file is part of And Bible (http://github.com/AndBible/and-bible).
 *
 * And Bible is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * And Bible is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with And Bible.
 * If not, see http://www.gnu.org/licenses/.
 *
 */
package net.bible.android.control.page

import android.app.Activity
import android.view.Menu
import net.bible.android.activity.R
import net.bible.android.view.activity.navigation.ChooseDictionaryWord
import net.bible.service.sword.SwordContentFacade
import net.bible.service.sword.SwordDocumentFacade
import org.crosswire.jsword.book.BookCategory
import org.crosswire.jsword.passage.Key

/** Reference to current passage shown by viewer
 *
 * @author Martin Denham [mjdenham at gmail dot com]
 */
class CurrentDictionaryPage internal constructor(
	swordContentFacade: SwordContentFacade,
	swordDocumentFacade: SwordDocumentFacade
) : CachedKeyPage(false, swordContentFacade, swordDocumentFacade), CurrentPage
{
    override val bookCategory = BookCategory.DICTIONARY

    override val keyChooserActivity: Class<out Activity?>?
        get() = ChooseDictionaryWord::class.java

    override fun doSetKey(key: Key?) {
        this._key = key
    }

    override fun next() {
        setKey(getKeyPlus(1))
    }

    override fun previous() {
        setKey(getKeyPlus(-1))
    }

    override fun updateOptionsMenu(menu: Menu) {
        super.updateOptionsMenu(menu)
        val menuItem = menu.findItem(R.id.bookmarksButton)
        if (menuItem != null) {
            menuItem.isEnabled = false
        }
    }

    override val isSingleKey = true
	override val key: Key? get() = _key

	/** can we enable the main menu search button
     */
    override val isSearchable = false

    companion object {
        private const val TAG = "CurrentDictionaryPage"
    }
}

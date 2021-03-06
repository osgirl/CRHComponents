/*
 * Copyright (C) 2011  Christian Roesch
 * 
 * This file is part of crhcomponents.
 * 
 * crhcomponents is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * crhcomponents is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with crhcomponents.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.croesch.contents.date;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

import com.github.croesch.logging.Log;

/**
 * {@link DateContent.MODE#LAZY} implementation of {@link DateContent}.
 * 
 * @author croesch
 * @since Date: Jul 3, 2011
 */
class DateLazyContent extends DateContent {

  /** generated */
  private static final long serialVersionUID = 530985421120602593L;

  //TODO save also: which one is the year-, month- and day-editor.. (wrapper object)
  /** list of editors that will edit the different parts of the date */
  private List<IDateLazyPartEditor> editors;

  /** text component to set the cursor */
  private final JTextComponent textComponent;

  /** the locale of the date content */
  private final Locale locale;

  /** the map that contains all available special characters */
  private final Map<String, DateSpecialChar> specialCharactersMap;

  /** instance to avoid masses of calendar instances */
  private final Calendar calendar = new GregorianCalendar();

  /**
   * Creates a new {@link DateLazyContent} that gives special support for date values. The given text component is used
   * to set the cursor to the correct position. The locale is used to fetch the format for the date.
   * 
   * @author croesch
   * @since Date: Jul 3, 2011
   * @param tc the text component for this document
   * @param loc the locale to fetch the date format from
   * @param specialCharsMap the map of {@link String}s and {@link DateSpecialChar} that contains all possible keys to
   *        enter to get a special date
   */
  public DateLazyContent(final JTextComponent tc, final Locale loc, final Map<String, DateSpecialChar> specialCharsMap) {
    this.specialCharactersMap = specialCharsMap;
    this.textComponent = tc;
    this.locale = loc;
    this.editors = DateComposition.getComposition(this.locale, MODE.LAZY);
  }

  @Override
  public final void insertString(int offs, final String str, final AttributeSet a) throws BadLocationException {
    // perform insertion into our really good date field :)
    if (str != null && str.length() > 0) {
      // we are sure that we have something to insert
      if (str.length() == 1) {
        // it's a single character ..
        performInsert(offs, str.charAt(0), a);
      } else {
        // there are several characters to insert
        for (int i = 0; i < str.length(); ++i) {
          // insert them on by one
          if (this.textComponent != null) {
            // if we have a text field (normal case)
            insertString(offs, str.substring(i, i + 1), a);
            // update the new offset to the new caret position
            offs = this.textComponent.getCaretPosition();
          } else {
            // if we have no textfield (unusual), we increment the offset by one per character
            insertString(offs + i, str.substring(i, i + 1), a);
          }
        }
      }
    }
  }

  /**
   * Performs the insertion of a single character.
   * 
   * @author croesch
   * @since Date: Jul 4, 2011
   * @param offs the offset where to insert basically
   * @param c the single character to insert
   * @param a the {@link AttributeSet}.
   * @throws BadLocationException if inserted on an invalid position
   */
  private void performInsert(int offs, final char c, final AttributeSet a) throws BadLocationException {

    boolean inserted = false;
    // the starting position of the current editor
    int startPos = 0;
    for (final IDateLazyPartEditor editor : this.editors) {
      // iterate over our editors .. and find the correct one to insert the character

      if (offs - editor.getSize() < 0) {
        // we have the editor that belongs to our offset, so try to insert
        final int z = editor.enterValue(c, offs);
        if (z == -1) {
          // the editor refused to insert the character
          // maybe there is another editor in the list that is able to insert the value
          offs = editor.getSize();
        } else {
          // the editor has inserted our character, so update the text representation (GUI)
          // simply, remove all and paste our generated text
          remove(0, getLength());
          super.insertString(0, getDateContent(), a);

          if (this.textComponent != null) {
            /*
             * the cursor position is the position where to insert the character (offs+startPos) + z (the number of
             * characters written)
             */
            this.textComponent.setCaretPosition(offs + startPos + z);
          }
          inserted = true;
          // we can end here
          break;
        }
      }

      // decrease the offs in the next editor
      offs -= editor.getSize();
      // and store the decreased value in startPos, so the original offs = (offs+startPos)
      startPos += editor.getSize();
    }

    // we were not able to insert this character, so maybe we have a special char
    if (!inserted) {
      /*
       * Don't care about null being return from the get-method, because this is no problem for the method that tries to
       * enter the special character
       */
      performEnterOfSpecialChar(a, this.specialCharactersMap.get(String.valueOf(c)));
    }
  }

  /**
   * Performs to enter the given special character. Will do nothing, if the given special character is <code>null</code>
   * 
   * @since Date: Sep 8, 2011
   * @param a the {@link AttributeSet} to enter text in the field
   * @param sc the special character to enter, can be <code>null</code>
   * @throws BadLocationException if something went wrong
   */
  private synchronized void performEnterOfSpecialChar(final AttributeSet a, final DateSpecialChar sc)
                                                                                                     throws BadLocationException {
    // do nothing if the special character is null
    if (sc != null) {
      int year = 1, month = 1, day = 1;
      for (final IDateLazyPartEditor e : this.editors) {
        if (e instanceof DateLazyYearEditor) {
          year = Integer.parseInt(e.getValue());
        } else if (e instanceof DateLazyMonEditor) {
          month = Integer.parseInt(e.getValue());
        } else if (e instanceof DateLazyDayEditor) {
          day = Integer.parseInt(e.getValue());
        }
      }

      this.calendar.setTimeInMillis(System.currentTimeMillis());
      // perform the update of the current date with values fetched from special char
      year = calculateNewValue(year, this.calendar.get(Calendar.YEAR), sc.getYearValue(), sc.getYearValueType());
      month = calculateNewValue(month, this.calendar.get(Calendar.MONTH) + 1, sc.getMonthValue(),
                                sc.getMonthValueType()) - 1;
      day = calculateNewValue(day, this.calendar.get(Calendar.DAY_OF_MONTH), sc.getDayValue(), sc.getDayValueType());

      // calculate valid date from special char
      this.calendar.set(year, month, day);
      // set the calculated date
      setDate(this.calendar.getTime());
      // insert the date into the text field
      remove(0, getLength());
      super.insertString(0, getDateContent(), a);
    }
  }

  /**
   * Calculates the new value based on the given data.
   * 
   * @since Date: Sep 8, 2011
   * @param curVal the current value of the field
   * @param todayVal the value of the field in todays date
   * @param value the value for the field fetched from special character
   * @param type the type of the value fetched from the special character
   * @return the calculated new value
   */
  private int calculateNewValue(final int curVal, final int todayVal, final int value, final ModificationType type) {
    switch (type) {
      case CONSTANT:
        return value;
      case OFFSET:
        return todayVal + value;
      default: //increment
        return curVal + value;
    }
  }

  @Override
  public final String getDateContent() {
    final StringBuilder sb = new StringBuilder();
    for (final IDateLazyPartEditor e : this.editors) {
      sb.append(e.getValue());
    }
    return sb.toString();
  }

  /**
   * Returns a {@link Calendar} object that contains the year, month and day values fetched from the editors.
   * 
   * @since Date: Sep 11, 2011
   * @return a {@link Calendar} object, containing year, month and day fetched from editors.
   */
  private Calendar getCalendarWithCurrentValues() {
    final Calendar cal = new GregorianCalendar();
    int day = 1;
    int month = 1;
    int year = 1;
    for (final IDateLazyPartEditor e : this.editors) {
      if (e instanceof DateLazyYearEditor) {
        year = Integer.parseInt(e.getValue());
      } else if (e instanceof DateLazyMonEditor) {
        month = Integer.parseInt(e.getValue()) - 1;
      } else if (e instanceof DateLazyDayEditor) {
        day = Integer.parseInt(e.getValue());
      }
    }

    cal.set(year, month, day);
    return cal;
  }

  @Override
  public final Date getDateWithoutTime() {
    final Calendar cal = getCalendarWithCurrentValues();
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTime();
  }

  @Override
  public Date getDateWithoutTimeOrNull() {
    if (getText() == null || getText().equals("")) {
      return null;
    }
    return getDateWithoutTime();
  }

  @Override
  public final Date getDate() {
    return getCalendarWithCurrentValues().getTime();
  }

  @Override
  public final Date getDateOrNull() {
    if (getText() == null || getText().equals("")) {
      return null;
    }
    return getDate();
  }

  @Override
  public final void setDate(final Date d) {
    try {
      remove(0, getLength());
    } catch (final BadLocationException e) {
      Log.error(e);
    }

    final Calendar cal = new GregorianCalendar();
    cal.setTime(d);
    this.editors = DateComposition.getComposition(this.locale, MODE.LAZY, cal.get(Calendar.DAY_OF_MONTH),
                                                  cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR));
  }
}

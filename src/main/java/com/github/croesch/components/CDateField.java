package com.github.croesch.components;

import java.util.Date;
import java.util.Locale;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.github.croesch.contents.date.DateContent;
import com.github.croesch.contents.date.DateContent.MODE;
import com.github.croesch.logging.Log;

/**
 * A text field that is specially made for date values.
 * 
 * @author croesch
 * @since Date: Mar 31, 2011 2:28:14 PM
 */
public class CDateField extends JTextField {

  /** generated */
  private static final long serialVersionUID = -8281302986312084742L;

  /** the date document of the class */
  private final DateContent document;

  /**
   * Generates a text field that is specially made for date values. The format of the date will be fetched from the
   * default locale of the system.
   * 
   * @since Date: Jul 3, 2011
   */
  public CDateField() {
    this(Locale.getDefault());
  }

  /**
   * Generates a text field that is specially made for date values. The format of the date will be fetched from the
   * given {@link Locale}.
   * 
   * @since Date: Jul 3, 2011
   * @param locale the {@link Locale} to fetch the format of the date from
   */
  public CDateField(final Locale locale) {
    this.document = DateContent.createDateContent(MODE.LAZY, this, locale);
    setDocument(this.document);
  }

  /**
   * Returns the date object containing the value currently stored in the date field.
   * 
   * @since Date: Jul 6, 2011
   * @return {@link Date} representing current value of the date field.
   */
  public final Date getDate() {
    return this.document.getDate();
  }

  /**
   * Sets the current value of the date field to the given date and displays it directly in the field.
   * 
   * @since Date: Jul 6, 2011
   * @param date the {@link Date} to set and display in the field.
   */
  public final void setDateAndDisplay(final Date date) {
    try {
      SwingUtilities.invokeAndWait(new Runnable() {

        @Override
        public void run() {
          CDateField.this.document.setDate(date);
          CDateField.this.setText(CDateField.this.document.getDateContent());
          CDateField.this.setCaretPosition(0);
        }
      });
    } catch (final Exception e) {
      Log.error(e);
    }
  }

  /**
   * Sets the current internal value of the date field to the given date and deletes the content of the field.
   * 
   * @since Date: Jul 6, 2011
   * @param date the {@link Date} to set.
   */
  public final void setDate(final Date date) {
    this.document.setDate(date);
  }
}

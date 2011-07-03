package com.github.croesch.contents.date;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

/**
 * Test methods for {@link DateLazyDayEditor}
 * 
 * @author croesch
 * @since Date: Jul 2, 2011
 */
public class DateLazyDayEditorTest {

  private DateLazyDayEditor editor;

  /**
   * Constructs the editor
   * 
   * @author croesch
   * @since Date: Jul 2, 2011
   */
  @Before
  public void setUp() {
    this.editor = new DateLazyDayEditor();
  }

  /**
   * Test method for {@link DateLazyDayEditor#DateLazyDayEditor()}.
   */
  @Test
  public final void testDateLazyDayEditor() {
    final int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    assertThat(this.editor.getValue()).isEqualTo(String.format("%02d", day));

    this.editor = new DateLazyDayEditor();
    assertThat(this.editor.getValue()).isEqualTo(String.format("%02d", day));

    this.editor = new DateLazyDayEditor(12);
    assertThat(this.editor.getValue()).isEqualTo("12");

    this.editor = new DateLazyDayEditor(22);
    assertThat(this.editor.getValue()).isEqualTo("22");

    this.editor = new DateLazyDayEditor(02);
    assertThat(this.editor.getValue()).isEqualTo("02");

    this.editor = new DateLazyDayEditor(31);
    assertThat(this.editor.getValue()).isEqualTo("31");

    this.editor = new DateLazyDayEditor(1);
    assertThat(this.editor.getValue()).isEqualTo("01");

    this.editor = new DateLazyDayEditor(19);
    assertThat(this.editor.getValue()).isEqualTo("19");

    this.editor = new DateLazyDayEditor(0);
    assertThat(this.editor.getValue()).isEqualTo(String.format("%02d", day));

    this.editor = new DateLazyDayEditor(32);
    assertThat(this.editor.getValue()).isEqualTo(String.format("%02d", day));

    this.editor = new DateLazyDayEditor(132);
    assertThat(this.editor.getValue()).isEqualTo(String.format("%02d", day));

    this.editor = new DateLazyDayEditor(-7);
    assertThat(this.editor.getValue()).isEqualTo(String.format("%02d", day));
  }

  /**
   * Test method for {@link DateLazyDayEditor#getSize()}.
   */
  @Test
  public final void testGetSize() {
    assertThat(this.editor.getSize()).isEqualTo(2);
  }

  /**
   * Test method for {@link DateLazyDayEditor#enterValue(String, int)}.
   */
  @Test
  public final void testEnterValue_SingleCharacter_First() {
    assertThat(this.editor.enterValue("0", 0)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(0)).isEqualTo('0');

    assertThat(this.editor.enterValue("1", 0)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(0)).isEqualTo('1');

    assertThat(this.editor.enterValue("2", 0)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(0)).isEqualTo('2');

    assertThat(this.editor.enterValue("3", 0)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(0)).isEqualTo('3');

    assertThat(this.editor.enterValue("4", 0)).isEqualTo(2);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('4');

    assertThat(this.editor.enterValue("5", 0)).isEqualTo(2);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('5');

    assertThat(this.editor.enterValue("6", 0)).isEqualTo(2);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('6');

    assertThat(this.editor.enterValue("7", 0)).isEqualTo(2);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('7');

    assertThat(this.editor.enterValue("8", 0)).isEqualTo(2);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('8');

    assertThat(this.editor.enterValue("9", 0)).isEqualTo(2);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('9');

    assertThat(this.editor.enterValue("a", 0)).isEqualTo(-1);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('9');

    assertThat(this.editor.enterValue("b", 0)).isEqualTo(-1);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('9');

  }

  /**
   * Test method for {@link DateLazyDayEditor#enterValue(String, int)}.
   */
  @Test
  public final void testEnterValue_SingleCharacter_Second() {
    assertThat(this.editor.enterValue("0", 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('0');

    assertThat(this.editor.enterValue("1", 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('1');

    assertThat(this.editor.enterValue("2", 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('2');

    assertThat(this.editor.enterValue("3", 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('3');

    assertThat(this.editor.enterValue("4", 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('4');

    assertThat(this.editor.enterValue("5", 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('5');

    assertThat(this.editor.enterValue("6", 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('6');

    assertThat(this.editor.enterValue("7", 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('7');

    assertThat(this.editor.enterValue("8", 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('8');

    assertThat(this.editor.enterValue("9", 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('9');

    assertThat(this.editor.enterValue("a", 1)).isEqualTo(-1);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('9');

    assertThat(this.editor.enterValue("b", 1)).isEqualTo(-1);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('9');
  }

  /**
   * Test method for {@link DateLazyDayEditor#enterValue(String, int)}.
   */
  @Test
  public final void testEnterValue_SingleCharacter_Invalid() {
    final String val = this.editor.getValue();
    assertThat(this.editor.enterValue("0", -1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("1", -1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("2", 2)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("3", 2)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("4", 3)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("5", 3)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("6", -2)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("7", -2)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("8", -3)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("9", -3)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("a", 10)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("b", 11)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
  }

  /**
   * Test method for {@link DateLazyDayEditor#enterValue(String, int)}.
   */
  @Test
  public final void testEnterValue_MultiCharacter() {
    final String val = this.editor.getValue();
    assertThat(this.editor.enterValue("01", 0)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("01", 1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("02", 0)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("03", 1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("04", 1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("05", 0)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("06", 2)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("07", 7)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("08", -1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("09", 2)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("0a", 0)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("0b", 3)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
  }

  /**
   * Test method for {@link DateLazyDayEditor#enterValue(String, int)}.
   */
  @Test
  public final void testEnterValue_Nothing() {
    assertThat(this.editor.enterValue("", 0)).isEqualTo(-1);
    assertThat(this.editor.enterValue("", 1)).isEqualTo(-1);
    assertThat(this.editor.enterValue(" ", 0)).isEqualTo(-1);
    assertThat(this.editor.enterValue(" ", 1)).isEqualTo(-1);
    assertThat(this.editor.enterValue(null, 1)).isEqualTo(-1);
    assertThat(this.editor.enterValue(null, 0)).isEqualTo(-1);
    assertThat(this.editor.enterValue("\n", 2)).isEqualTo(-1);
    assertThat(this.editor.enterValue("\t", 7)).isEqualTo(-1);
    assertThat(this.editor.enterValue(null, -1)).isEqualTo(-1);
    assertThat(this.editor.enterValue(null, 2)).isEqualTo(-1);
    assertThat(this.editor.enterValue("\n", 0)).isEqualTo(-1);
    assertThat(this.editor.enterValue("\n", 3)).isEqualTo(-1);
  }

  /**
   * Test method for {@link DateLazyDayEditor#equals(Object)}.
   */
  @Test
  public final void testEquals() {
    assertThat(this.editor).isEqualTo(this.editor);

    assertThat(new DateLazyDayEditor(2)).isEqualTo(new DateLazyDayEditor(2));
    assertThat(new DateLazyDayEditor(12)).isEqualTo(new DateLazyDayEditor(12));
    assertThat(new DateLazyDayEditor(22)).isEqualTo(new DateLazyDayEditor(22));
    assertThat(new DateLazyDayEditor(30)).isEqualTo(new DateLazyDayEditor(30));
    assertThat(new DateLazyDayEditor(32)).isEqualTo(new DateLazyDayEditor(32));

    assertThat(new DateLazyDayEditor(2)).isNotEqualTo(null);
    assertThat(new DateLazyDayEditor(2)).isNotEqualTo("02");
    assertThat(new DateLazyDayEditor(2)).isNotEqualTo(new DateLazyDayEditor(1));
    assertThat(new DateLazyDayEditor(22)).isNotEqualTo(new DateLazyDayEditor(11));
    assertThat(new DateLazyDayEditor(22)).isNotEqualTo(new DateLazyDayEditor(21));
    assertThat(new DateLazyDayEditor(30)).isNotEqualTo(new DateLazyDayEditor(31));
    assertThat(new DateLazyDayEditor(32)).isNotEqualTo(new DateLazyDayEditor(31));
  }

  /**
   * Test method for {@link DateLazyDayEditor#toString()}
   */
  @Test
  public final void testToString() {
    final int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    assertThat(this.editor.getValue()).isEqualTo(String.format("%02d", day));
    assertThat(this.editor.toString()).isEqualTo(String.format("%02d", day));
    this.editor = new DateLazyDayEditor(12);
    assertThat(this.editor.getValue()).isEqualTo("12");
    assertThat(this.editor.toString()).isEqualTo("12");
    this.editor = new DateLazyDayEditor(29);
    assertThat(this.editor.getValue()).isEqualTo("29");
    assertThat(this.editor.toString()).isEqualTo("29");
    this.editor = new DateLazyDayEditor(3);
    assertThat(this.editor.getValue()).isEqualTo("03");
    assertThat(this.editor.toString()).isEqualTo("03");
  }
}

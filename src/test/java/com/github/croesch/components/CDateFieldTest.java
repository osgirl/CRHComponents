package com.github.croesch.components;

import static com.github.croesch.TestUtil.assertDateHasValues;
import static org.fest.assertions.Assertions.assertThat;

import java.awt.Dimension;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JTextField;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JTextComponentFixture;
import org.fest.swing.junit.testcase.FestSwingJUnitTestCase;
import org.junit.Test;

/**
 * Provides test methods for {@link CDateField}.
 * 
 * @author croesch
 * @since Date: Jul 3, 2011
 */
public class CDateFieldTest extends FestSwingJUnitTestCase {

  private JTextComponentFixture field;

  private Locale l;

  @Override
  public void onSetUp() {
    this.l = Locale.getDefault();
    Locale.setDefault(Locale.GERMAN);

    robot().settings().eventPostingDelay(50);
    robot().settings().delayBetweenEvents(50);

    this.field = getDateField(Locale.GERMAN);
  }

  private final JTextComponentFixture getDateField(final Locale l) {
    final FrameFixture f = new FrameFixture(robot(), GuiActionRunner.execute(new GuiQuery<JFrame>() {

      @Override
      protected JFrame executeInEDT() throws Throwable {
        final JFrame f = new JFrame();
        f.add(new CDateField(l));
        f.setPreferredSize(new Dimension(100, 50));
        return f;
      }
    }));
    f.show();
    return f.textBox();
  }

  @Override
  protected void onTearDown() {
    Locale.setDefault(this.l);
    super.onTearDown();
  }

  @Test
  public final void testEnterDate1() {
    this.field.enterText("1");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(1);
    this.field.enterText("2");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(2);
    this.field.enterText(".");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(3);
    this.field.enterText("0");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(4);
    this.field.enterText("1");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(5);
    this.field.enterText(".");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(6);
    this.field.enterText("1");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(7);
    this.field.enterText("9");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(8);
    this.field.enterText("7");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(9);
    this.field.enterText("6");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(10);
    this.field.requireText("12.01.1976");
    assertDateHasValues(((CDateField) this.field.target).getDate(), 12, 1, 1976);
  }

  @Test
  public final void testEnterDate2() {
    final Calendar cal = new GregorianCalendar();
    cal.set(1976, 0, 12);
    ((CDateField) this.field.target).setDate(cal.getTime());
    this.field.enterText(".");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(3);
    this.field.enterText("0");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(4);
    this.field.enterText("1");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(5);
    this.field.enterText("1");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(7);
    this.field.enterText("9");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(8);
    this.field.enterText("7");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(9);
    this.field.enterText("6");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(10);
    this.field.requireText("12.01.1976");
    assertDateHasValues(((CDateField) this.field.target).getDate(), 12, 1, 1976);
  }

  @Test
  public final void testEnterDate3() {
    this.field.enterText("0");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(1);
    this.field.enterText("1");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(2);
    this.field.enterText("0");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(4);
    this.field.enterText("1");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(5);
    this.field.enterText("0");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(7);
    this.field.enterText("0");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(8);
    this.field.requireText("01.01.2000");
    assertDateHasValues(((CDateField) this.field.target).getDate(), 1, 1, 2000);

    this.field.deleteText();

    this.field.enterText("4");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(2);
    this.field.enterText("4");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(5);
    this.field.enterText("4");
    this.field.requireText("04.04.2004");
    assertDateHasValues(((CDateField) this.field.target).getDate(), 4, 4, 2004);
  }

  @Test
  public final void testPasteText1() {
    final Calendar cal = new GregorianCalendar();
    cal.set(2000, 0, 1);
    ((CDateField) this.field.target).setDateAndDisplay(cal.getTime());
    this.field.requireText("01.01.2000");
    assertDateHasValues(((CDateField) this.field.target).getDate(), 1, 1, 2000);

    this.field.deleteText();

    GuiActionRunner.execute(new GuiTask() {

      @Override
      protected void executeInEDT() throws Throwable {
        final JTextField tf;
        tf = new JTextField("444");
        tf.selectAll();
        tf.copy();
        CDateFieldTest.this.field.target.paste();
      }

    });
    this.field.requireText("04.04.2004");
    assertDateHasValues(((CDateField) this.field.target).getDate(), 4, 4, 2004);
  }

  @Test
  public final void testPasteText2() {
    final FrameFixture f = new FrameFixture(robot(), GuiActionRunner.execute(new GuiQuery<JFrame>() {

      @Override
      protected JFrame executeInEDT() throws Throwable {
        final JFrame f = new JFrame();
        f.add(new CDateField());
        f.setPreferredSize(new Dimension(100, 50));
        return f;
      }
    }));
    f.show();
    this.field = f.textBox();

    this.field.enterText("010100");
    this.field.requireText("01.01.2000");
    assertDateHasValues(((CDateField) this.field.target).getDate(), 1, 1, 2000);

    this.field.deleteText();

    GuiActionRunner.execute(new GuiTask() {

      @Override
      protected void executeInEDT() throws Throwable {
        final JTextField tf;
        tf = new JTextField("444");
        tf.selectAll();
        tf.copy();
        CDateFieldTest.this.field.target.paste();
      }

    });
    this.field.requireText("04.04.2004");
    assertDateHasValues(((CDateField) this.field.target).getDate(), 4, 4, 2004);
  }
}

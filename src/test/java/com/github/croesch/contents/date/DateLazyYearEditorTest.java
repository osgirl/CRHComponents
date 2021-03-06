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

import static org.fest.assertions.Assertions.assertThat;

import java.util.Calendar;

import org.junit.Test;

import com.github.croesch.DefaultTestCase;

/**
 * Test methods for {@link DateLazyYearEditor}
 * 
 * @author croesch
 * @since Date: Jul 2, 2011
 */
public class DateLazyYearEditorTest extends DefaultTestCase {

  private DateLazyYearEditor editor;

  /**
   * Constructs the editor
   * 
   * @author croesch
   * @since Date: Jul 2, 2011
   */
  @Override
  public void setUpDetails() {
    this.editor = new DateLazyYearEditor();
  }

  /**
   * Test method for {@link DateLazyYearEditor#DateLazyYearEditor()}.
   */
  @Test
  public final void testDateLazyYearEditor() {
    final int year = Calendar.getInstance().get(Calendar.YEAR);
    assertThat(this.editor.getValue()).isEqualTo(String.format("%04d", year));

    this.editor = new DateLazyYearEditor();
    assertThat(this.editor.getValue()).isEqualTo(String.format("%04d", year));

    this.editor = new DateLazyYearEditor(12);
    assertThat(this.editor.getValue()).isEqualTo("0012");

    this.editor = new DateLazyYearEditor(2010);
    assertThat(this.editor.getValue()).isEqualTo("2010");

    this.editor = new DateLazyYearEditor(2011);
    assertThat(this.editor.getValue()).isEqualTo("2011");

    this.editor = new DateLazyYearEditor(1903);
    assertThat(this.editor.getValue()).isEqualTo("1903");

    this.editor = new DateLazyYearEditor(1803);
    assertThat(this.editor.getValue()).isEqualTo("1803");

    this.editor = new DateLazyYearEditor(2080);
    assertThat(this.editor.getValue()).isEqualTo("2080");

    this.editor = new DateLazyYearEditor(0);
    assertThat(this.editor.getValue()).isEqualTo(String.format("%04d", year));

    this.editor = new DateLazyYearEditor(10000);
    assertThat(this.editor.getValue()).isEqualTo(String.format("%04d", year));

    this.editor = new DateLazyYearEditor(-132);
    assertThat(this.editor.getValue()).isEqualTo(String.format("%04d", year));

    this.editor = new DateLazyYearEditor(-7);
    assertThat(this.editor.getValue()).isEqualTo(String.format("%04d", year));
  }

  /**
   * Test method for {@link DateLazyYearEditor#getSize()}.
   */
  @Test
  public final void testGetSize() {
    assertThat(this.editor.getSize()).isEqualTo(4);
  }

  /**
   * Test method for {@link DateLazyYearEditor#enterValue(char, int)}.
   */
  @Test
  public final void testEnterValue_SingleCharacter_First() {
    assertThat(this.editor.enterValue('0', 0)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('0');

    assertThat(this.editor.enterValue('1', 0)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('1');

    assertThat(this.editor.enterValue('2', 0)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('2');

    assertThat(this.editor.enterValue('3', 0)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('3');

    assertThat(this.editor.enterValue('4', 0)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('4');

    assertThat(this.editor.enterValue('5', 0)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('5');

    assertThat(this.editor.enterValue('6', 0)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('6');

    assertThat(this.editor.enterValue('7', 0)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('7');

    assertThat(this.editor.enterValue('8', 0)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('8');

    assertThat(this.editor.enterValue('9', 0)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('9');

    assertThat(this.editor.enterValue('a', 0)).isEqualTo(-1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('9');

    assertThat(this.editor.enterValue('b', 0)).isEqualTo(-1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('9');

  }

  /**
   * Test method for {@link DateLazyYearEditor#enterValue(char, int)}.
   */
  @Test
  public final void testEnterValue_SingleCharacter_Second() {
    char c3 = this.editor.getValue().charAt(3);
    assertThat(this.editor.enterValue('0', 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('0');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    if (c3 == '0' || c3 == '1' || c3 == '2' || c3 == '3') {
      assertThat(this.editor.getValue().charAt(0)).isEqualTo('2');
      assertThat(this.editor.getValue().charAt(1)).isEqualTo('0');
    } else {
      assertThat(this.editor.getValue().charAt(0)).isEqualTo('1');
      assertThat(this.editor.getValue().charAt(1)).isEqualTo('9');
    }

    c3 = this.editor.getValue().charAt(3);
    assertThat(this.editor.enterValue('1', 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('1');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    if (c3 == '0' || c3 == '1' || c3 == '2' || c3 == '3') {
      assertThat(this.editor.getValue().charAt(0)).isEqualTo('2');
      assertThat(this.editor.getValue().charAt(1)).isEqualTo('0');
    } else {
      assertThat(this.editor.getValue().charAt(0)).isEqualTo('1');
      assertThat(this.editor.getValue().charAt(1)).isEqualTo('9');
    }

    c3 = this.editor.getValue().charAt(3);
    assertThat(this.editor.enterValue('2', 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('2');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    if (c3 == '0' || c3 == '1' || c3 == '2' || c3 == '3') {
      assertThat(this.editor.getValue().charAt(0)).isEqualTo('2');
      assertThat(this.editor.getValue().charAt(1)).isEqualTo('0');
    } else {
      assertThat(this.editor.getValue().charAt(0)).isEqualTo('1');
      assertThat(this.editor.getValue().charAt(1)).isEqualTo('9');
    }

    c3 = this.editor.getValue().charAt(3);
    assertThat(this.editor.enterValue('3', 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('3');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    if (c3 == '0' || c3 == '1' || c3 == '2' || c3 == '3') {
      assertThat(this.editor.getValue().charAt(0)).isEqualTo('2');
      assertThat(this.editor.getValue().charAt(1)).isEqualTo('0');
    } else {
      assertThat(this.editor.getValue().charAt(0)).isEqualTo('1');
      assertThat(this.editor.getValue().charAt(1)).isEqualTo('9');
    }

    c3 = this.editor.getValue().charAt(3);
    assertThat(this.editor.enterValue('4', 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('4');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    if (c3 == '0' || c3 == '1' || c3 == '2' || c3 == '3') {
      assertThat(this.editor.getValue().charAt(0)).isEqualTo('2');
      assertThat(this.editor.getValue().charAt(1)).isEqualTo('0');
    } else {
      assertThat(this.editor.getValue().charAt(0)).isEqualTo('1');
      assertThat(this.editor.getValue().charAt(1)).isEqualTo('9');
    }

    c3 = this.editor.getValue().charAt(3);
    assertThat(this.editor.enterValue('5', 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('5');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    if (c3 == '0' || c3 == '1' || c3 == '2' || c3 == '3') {
      assertThat(this.editor.getValue().charAt(0)).isEqualTo('2');
      assertThat(this.editor.getValue().charAt(1)).isEqualTo('0');
    } else {
      assertThat(this.editor.getValue().charAt(0)).isEqualTo('1');
      assertThat(this.editor.getValue().charAt(1)).isEqualTo('9');
    }

    c3 = this.editor.getValue().charAt(3);
    assertThat(this.editor.enterValue('6', 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('6');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    if (c3 == '0' || c3 == '1' || c3 == '2' || c3 == '3') {
      assertThat(this.editor.getValue().charAt(0)).isEqualTo('2');
      assertThat(this.editor.getValue().charAt(1)).isEqualTo('0');
    } else {
      assertThat(this.editor.getValue().charAt(0)).isEqualTo('1');
      assertThat(this.editor.getValue().charAt(1)).isEqualTo('9');
    }

    c3 = this.editor.getValue().charAt(3);
    assertThat(this.editor.enterValue('7', 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('7');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    if (c3 == '0' || c3 == '1' || c3 == '2' || c3 == '3') {
      assertThat(this.editor.getValue().charAt(0)).isEqualTo('2');
      assertThat(this.editor.getValue().charAt(1)).isEqualTo('0');
    } else {
      assertThat(this.editor.getValue().charAt(0)).isEqualTo('1');
      assertThat(this.editor.getValue().charAt(1)).isEqualTo('9');
    }

    c3 = this.editor.getValue().charAt(3);
    assertThat(this.editor.enterValue('8', 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('8');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    if (c3 == '0' || c3 == '1' || c3 == '2' || c3 == '3') {
      assertThat(this.editor.getValue().charAt(0)).isEqualTo('2');
      assertThat(this.editor.getValue().charAt(1)).isEqualTo('0');
    } else {
      assertThat(this.editor.getValue().charAt(0)).isEqualTo('1');
      assertThat(this.editor.getValue().charAt(1)).isEqualTo('9');
    }

    c3 = this.editor.getValue().charAt(3);
    assertThat(this.editor.enterValue('9', 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('9');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    if (c3 == '0' || c3 == '1' || c3 == '2' || c3 == '3') {
      assertThat(this.editor.getValue().charAt(0)).isEqualTo('2');
      assertThat(this.editor.getValue().charAt(1)).isEqualTo('0');
    } else {
      assertThat(this.editor.getValue().charAt(0)).isEqualTo('1');
      assertThat(this.editor.getValue().charAt(1)).isEqualTo('9');
    }

    assertThat(this.editor.enterValue('a', 1)).isEqualTo(-1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('9');

    assertThat(this.editor.enterValue('b', 1)).isEqualTo(-1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('9');
  }

  /**
   * Test method for {@link DateLazyYearEditor#enterValue(char, int)}.
   */
  @Test
  public final void testEnterValue_SingleCharacter_Third() {
    char c3 = this.editor.getValue().charAt(3);
    char c2 = this.editor.getValue().charAt(2);
    assertThat(this.editor.enterValue('0', 2)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('0');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo(c2);
    assertThat(this.editor.getValue().charAt(0)).isEqualTo('1');

    c3 = this.editor.getValue().charAt(3);
    c2 = this.editor.getValue().charAt(2);
    assertThat(this.editor.enterValue('1', 2)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('1');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo(c2);
    assertThat(this.editor.getValue().charAt(0)).isEqualTo('1');

    c3 = this.editor.getValue().charAt(3);
    c2 = this.editor.getValue().charAt(2);
    assertThat(this.editor.enterValue('2', 2)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('2');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo(c2);
    assertThat(this.editor.getValue().charAt(0)).isEqualTo('1');

    c3 = this.editor.getValue().charAt(3);
    c2 = this.editor.getValue().charAt(2);
    assertThat(this.editor.enterValue('3', 2)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('3');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo(c2);
    assertThat(this.editor.getValue().charAt(0)).isEqualTo('1');

    c3 = this.editor.getValue().charAt(3);
    c2 = this.editor.getValue().charAt(2);
    assertThat(this.editor.enterValue('4', 2)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('4');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo(c2);
    assertThat(this.editor.getValue().charAt(0)).isEqualTo('1');

    c3 = this.editor.getValue().charAt(3);
    c2 = this.editor.getValue().charAt(2);
    assertThat(this.editor.enterValue('5', 2)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('5');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo(c2);
    assertThat(this.editor.getValue().charAt(0)).isEqualTo('1');

    c3 = this.editor.getValue().charAt(3);
    c2 = this.editor.getValue().charAt(2);
    assertThat(this.editor.enterValue('6', 2)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('6');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo(c2);
    assertThat(this.editor.getValue().charAt(0)).isEqualTo('1');

    c3 = this.editor.getValue().charAt(3);
    c2 = this.editor.getValue().charAt(2);
    assertThat(this.editor.enterValue('7', 2)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('7');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo(c2);
    assertThat(this.editor.getValue().charAt(0)).isEqualTo('1');

    c3 = this.editor.getValue().charAt(3);
    c2 = this.editor.getValue().charAt(2);
    assertThat(this.editor.enterValue('8', 2)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('8');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo(c2);
    assertThat(this.editor.getValue().charAt(0)).isEqualTo('1');

    c3 = this.editor.getValue().charAt(3);
    c2 = this.editor.getValue().charAt(2);
    assertThat(this.editor.enterValue('9', 2)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('9');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo(c2);
    assertThat(this.editor.getValue().charAt(0)).isEqualTo('1');

    assertThat(this.editor.enterValue('a', 2)).isEqualTo(-1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('9');

    assertThat(this.editor.enterValue('b', 2)).isEqualTo(-1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('9');
  }

  /**
   * Test method for {@link DateLazyYearEditor#enterValue(char, int)}.
   */
  @Test
  public final void testEnterValue_SingleCharacter_Fourth() {
    char c3 = this.editor.getValue().charAt(3);
    char c2 = this.editor.getValue().charAt(2);
    char c1 = this.editor.getValue().charAt(1);
    assertThat(this.editor.enterValue('0', 3)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('0');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo(c2);
    assertThat(this.editor.getValue().charAt(0)).isEqualTo(c1);

    c3 = this.editor.getValue().charAt(3);
    c2 = this.editor.getValue().charAt(2);
    c1 = this.editor.getValue().charAt(1);
    assertThat(this.editor.enterValue('1', 3)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('1');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo(c2);
    assertThat(this.editor.getValue().charAt(0)).isEqualTo(c1);

    c3 = this.editor.getValue().charAt(3);
    c2 = this.editor.getValue().charAt(2);
    c1 = this.editor.getValue().charAt(1);
    assertThat(this.editor.enterValue('2', 3)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('2');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo(c2);
    assertThat(this.editor.getValue().charAt(0)).isEqualTo(c1);

    c3 = this.editor.getValue().charAt(3);
    c2 = this.editor.getValue().charAt(2);
    c1 = this.editor.getValue().charAt(1);
    assertThat(this.editor.enterValue('3', 3)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('3');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo(c2);
    assertThat(this.editor.getValue().charAt(0)).isEqualTo(c1);

    c3 = this.editor.getValue().charAt(3);
    c2 = this.editor.getValue().charAt(2);
    c1 = this.editor.getValue().charAt(1);
    assertThat(this.editor.enterValue('4', 3)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('4');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo(c2);
    assertThat(this.editor.getValue().charAt(0)).isEqualTo(c1);

    c3 = this.editor.getValue().charAt(3);
    c2 = this.editor.getValue().charAt(2);
    c1 = this.editor.getValue().charAt(1);
    assertThat(this.editor.enterValue('5', 3)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('5');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo(c2);
    assertThat(this.editor.getValue().charAt(0)).isEqualTo(c1);

    c3 = this.editor.getValue().charAt(3);
    c2 = this.editor.getValue().charAt(2);
    c1 = this.editor.getValue().charAt(1);
    assertThat(this.editor.enterValue('6', 3)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('6');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo(c2);
    assertThat(this.editor.getValue().charAt(0)).isEqualTo(c1);

    c3 = this.editor.getValue().charAt(3);
    c2 = this.editor.getValue().charAt(2);
    c1 = this.editor.getValue().charAt(1);
    assertThat(this.editor.enterValue('7', 3)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('7');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo(c2);
    assertThat(this.editor.getValue().charAt(0)).isEqualTo(c1);

    c3 = this.editor.getValue().charAt(3);
    c2 = this.editor.getValue().charAt(2);
    c1 = this.editor.getValue().charAt(1);
    assertThat(this.editor.enterValue('8', 3)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('8');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo(c2);
    assertThat(this.editor.getValue().charAt(0)).isEqualTo(c1);

    c3 = this.editor.getValue().charAt(3);
    c2 = this.editor.getValue().charAt(2);
    c1 = this.editor.getValue().charAt(1);
    assertThat(this.editor.enterValue('9', 3)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('9');
    assertThat(this.editor.getValue().charAt(2)).isEqualTo(c3);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo(c2);
    assertThat(this.editor.getValue().charAt(0)).isEqualTo(c1);

    assertThat(this.editor.enterValue('a', 3)).isEqualTo(-1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('9');

    assertThat(this.editor.enterValue('b', 3)).isEqualTo(-1);
    assertThat(this.editor.getValue().charAt(3)).isEqualTo('9');
  }

  /**
   */
  @Test
  public final void testEnterValue_SingleCharacter_Invalid() {
    final String val = this.editor.getValue();
    assertThat(this.editor.enterValue('0', -1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue('1', -1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue('2', 4)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue('3', 4)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue('4', 5)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue('5', 5)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue('6', -2)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue('7', -2)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue('8', -3)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue('9', -3)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue('a', 10)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue('b', 11)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue('?', 1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue('?', 0)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue('+', 1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue('+', 0)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
  }

  /**
   * Test method for {@link DateLazyYearEditor#enterValue(char, int)}.
   */
  @Test
  public final void testEnterValue_Nothing() {
    assertThat(this.editor.enterValue(' ', 0)).isEqualTo(-1);
    assertThat(this.editor.enterValue(' ', 1)).isEqualTo(-1);
    assertThat(this.editor.enterValue('\n', 2)).isEqualTo(-1);
    assertThat(this.editor.enterValue('\t', 7)).isEqualTo(-1);
    assertThat(this.editor.enterValue('\n', 0)).isEqualTo(-1);
    assertThat(this.editor.enterValue('\n', 3)).isEqualTo(-1);
  }

  /**
   * Test method for {@link DateLazyYearEditor#equals(Object)}.
   */
  @Test
  public final void testEquals() {
    assertThat(this.editor).isEqualTo(this.editor);

    assertThat(new DateLazyYearEditor(2)).isEqualTo(new DateLazyYearEditor(2));
    assertThat(new DateLazyYearEditor(2012)).isEqualTo(new DateLazyYearEditor(2012));
    assertThat(new DateLazyYearEditor(1901)).isEqualTo(new DateLazyYearEditor(1901));
    assertThat(new DateLazyYearEditor(1877)).isEqualTo(new DateLazyYearEditor(1877));
    assertThat(new DateLazyYearEditor(10032)).isEqualTo(new DateLazyYearEditor(10031));

    assertThat(new DateLazyYearEditor(2000)).isNotEqualTo(null);
    assertThat(new DateLazyYearEditor(2000)).isNotEqualTo("2000");
    assertThat(new DateLazyYearEditor(2000)).isNotEqualTo(new DateLazyYearEditor(1999));
    assertThat(new DateLazyYearEditor(2010)).isNotEqualTo(new DateLazyYearEditor(2011));
    assertThat(new DateLazyYearEditor(1909)).isNotEqualTo(new DateLazyYearEditor(1910));
    assertThat(new DateLazyYearEditor(1907)).isNotEqualTo(new DateLazyYearEditor(1908));
    assertThat(new DateLazyYearEditor(2014)).isNotEqualTo(new DateLazyYearEditor(2013));

    assertThat(new DateLazyYearEditor(2011)).isNotEqualTo(new FakeEditor(2011));
  }

  private static class FakeEditor extends DateLazyYearEditor {
    public FakeEditor(final int i) {
      super(i);
    }
  }

  /**
   * Test method for {@link DateLazyYearEditor#toString()}
   */
  @Test
  public final void testToString() {
    final int year = Calendar.getInstance().get(Calendar.YEAR);
    assertThat(this.editor.getValue()).isEqualTo(String.format("%04d", year));
    assertThat(this.editor.toString()).isEqualTo(String.format("%04d", year));
    this.editor = new DateLazyYearEditor(12);
    assertThat(this.editor.getValue()).isEqualTo("0012");
    assertThat(this.editor.toString()).isEqualTo("0012");
    this.editor = new DateLazyYearEditor(2002);
    assertThat(this.editor.getValue()).isEqualTo("2002");
    assertThat(this.editor.toString()).isEqualTo("2002");
    this.editor = new DateLazyYearEditor(1437);
    assertThat(this.editor.getValue()).isEqualTo("1437");
    assertThat(this.editor.toString()).isEqualTo("1437");
  }
}

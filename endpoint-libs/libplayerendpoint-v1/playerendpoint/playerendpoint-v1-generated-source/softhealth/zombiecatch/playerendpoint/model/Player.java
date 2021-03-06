/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2014-06-09 16:41:44 UTC)
 * on 2014-06-12 at 14:51:53 UTC 
 * Modify at your own risk.
 */

package softhealth.zombiecatch.playerendpoint.model;

/**
 * Model definition for Player.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the playerendpoint. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Player extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String gameTitle;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Boolean isHuman;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Key key;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double score;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer sneakLvl;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String userEmail;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double userLat;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double userLon;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getGameTitle() {
    return gameTitle;
  }

  /**
   * @param gameTitle gameTitle or {@code null} for none
   */
  public Player setGameTitle(java.lang.String gameTitle) {
    this.gameTitle = gameTitle;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Boolean getIsHuman() {
    return isHuman;
  }

  /**
   * @param isHuman isHuman or {@code null} for none
   */
  public Player setIsHuman(java.lang.Boolean isHuman) {
    this.isHuman = isHuman;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public Key getKey() {
    return key;
  }

  /**
   * @param key key or {@code null} for none
   */
  public Player setKey(Key key) {
    this.key = key;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getScore() {
    return score;
  }

  /**
   * @param score score or {@code null} for none
   */
  public Player setScore(java.lang.Double score) {
    this.score = score;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getSneakLvl() {
    return sneakLvl;
  }

  /**
   * @param sneakLvl sneakLvl or {@code null} for none
   */
  public Player setSneakLvl(java.lang.Integer sneakLvl) {
    this.sneakLvl = sneakLvl;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getUserEmail() {
    return userEmail;
  }

  /**
   * @param userEmail userEmail or {@code null} for none
   */
  public Player setUserEmail(java.lang.String userEmail) {
    this.userEmail = userEmail;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getUserLat() {
    return userLat;
  }

  /**
   * @param userLat userLat or {@code null} for none
   */
  public Player setUserLat(java.lang.Double userLat) {
    this.userLat = userLat;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getUserLon() {
    return userLon;
  }

  /**
   * @param userLon userLon or {@code null} for none
   */
  public Player setUserLon(java.lang.Double userLon) {
    this.userLon = userLon;
    return this;
  }

  @Override
  public Player set(String fieldName, Object value) {
    return (Player) super.set(fieldName, value);
  }

  @Override
  public Player clone() {
    return (Player) super.clone();
  }

}

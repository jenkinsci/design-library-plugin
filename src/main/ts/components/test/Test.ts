import { LitElement, html } from 'lit';
import { customElement } from 'lit/decorators'
import Styles from './Test.styles';

@customElement('swc-test')
export class Test extends LitElement {
  static styles = [ Styles ];

  render() {
    return html`swc-test`;
  }
}
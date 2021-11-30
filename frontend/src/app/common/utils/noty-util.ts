import * as Noty from "noty";

/**
 * Toast alert util to show error/success messages on UI
 * @author Rahul Mistry
 * @since 19-June-2021
 * @version 1.0
 *
 */
export class NotyUtil {
  /**
   * Show success toast
   * @param message 
   * @param timeout 
   */
  public static success(message: string, timeout: number = 1500) {
    new Noty({
      theme: 'relax',
      closeWith: ['click', 'button'],
      timeout,
      type: 'success',
      layout: 'topRight',
      text: message
    }).show();
  }

  /**
   * Show warning toast
   * @param message 
   * @param timeout 
   */
  public static warning(message: string, timeout: number = 3000) {
    new Noty({
      theme: 'relax',
      closeWith: ['click', 'button'],
      timeout,
      type: 'warning',
      layout: 'topRight',
      text: message
    }).show();
  }

  /**
   * Show error toast
   * @param message 
   * @param timeout 
   */
  public static error(message: string, timeout: number = 3000) {
    new Noty({
      theme: 'relax',
      closeWith: ['click', 'button'],
      timeout,
      type: 'error',
      layout: 'topRight',
      text: message
    }).show();
  }
}

export function createAlert(content, url) {

    let $div = document.createElement('div');
    $div.classList.add("alert", "alert-success", "alert-dismissible", "fade", "show", "alert-popup");
    $div.setAttribute('role', 'alert');

    let $strong = document.createElement('strong');
    $strong.textContent = content;

    let $button = document.createElement('button');
    $button.type = 'button';
    $button.classList.add("btn-close");
    $button.setAttribute('data-bs-dismiss', 'alert');
    $button.setAttribute('aria-label', 'Close');

    $button.addEventListener('click', () => {
        location.href = url;
    });

    $div.appendChild($strong);
    $div.appendChild($button);

    return $div;
}
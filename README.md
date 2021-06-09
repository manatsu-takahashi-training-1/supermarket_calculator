# スーパーの支払金額計算

スーパーで買い物したときの支払金額を計算する

以下の商品リストがあるとする。先頭の数字は商品番号。

1. りんご 100円
2. みかん 40円
3. ぶどう 150円
4. のり弁 350円
5. しゃけ弁 400円
6. タバコ 420円
7. メンソールタバコ 440円
8. ライター 100円
9. お茶 80円
10. コーヒー 100円

以下の順番で、仕様を追加・実装していく。

## お題1 合計金額

商品番号と個数を複数組、引数として受け取り、合計金額を計算する関数を書いてみよう。

ヒント: 複数のものを受け取るために、配列やリストで一括で渡す方法がある。あるいは、1つ渡す関数を何回も呼び出して、最後に合計金額を計算する関数を呼び出すという形式もある。両方のアプローチをTDDで実装し見比べて、どちらが良いか判断してみよう。

いきなり書くのが難しかったら、以下の補題をやってみるとよい。

### 補題1

商品番号を渡すと、1個あたりの金額を計算する関数を書いてみよう。

### 補題2

商品番号を複数渡すと、個数1個として金額を合計する関数を書いてみよう。

## お題2 消費税

商品リストの金額は外税なので、合計金額に消費税8％を足して、支払金額を返すようにしよう。

## お題3 タバコの消費税

タバコの価格には消費税が含まれているので(内税)、消費税の計算からタバコは除かないといけない。

## お題4 割引

リンゴは1個100円だが、3つ買うと280円になる。

## お題5 おまけ

なんでも、同じものを10個買うと、1個おまけでもらえる。11個で10個ぶんの金額（12個で11個分、20個で19個分、22個で20個ぶん、...)という形で実現しよう。

## お題6 おまけのライター

タバコを1カートン(10個)買うと、ライターがおまけでもらえる。引数にライターがあったら無料になるというふうに実現しよう。

## お題7 お弁当

弁当類と飲み物(お茶とコーヒー)をいっしょに買うと、20円引きになる。

## お題8 サービスしすぎない

お題4～7のようなサービスは、同じ商品については重複しない。一番安くなるものをひとつだけ適用する。

## お題9 タイムセール

お弁当は20時を過ぎると半額になる。

## お題10 タイムセールとサービス

お弁当のタイムセールは、他のサービスと重複してよい。


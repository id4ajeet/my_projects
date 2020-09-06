/*
 * (c) Copyright 2014 Brite:Bill Ltd.
 *
 * 23 Windsor Place, Dublin 2, Ireland
 * info@britebill.com
 * +353 1 661 9426
 */
package com.ajeet.others;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/*
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;*/

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class AjeetTest {

    @Test
    public void majority() {

        int N = 11;
        int[] data = {1, 2, 3, 4, 5, 3, 2, 3, 2, 3, 4};
        int mindex = 0;
        int c = 1;

        for (int i = 1; i < N; i++) {
            c = (data[i] == data[mindex]) ? c + 1 : c - 1;

            if (c == 0) {
                mindex = i;
                c = 1;
            }
            System.out.println(c + " " + mindex + " " + data[mindex] + " " + data[i]);
        }
        c = 0;
        for (int i = 0; i < N; i++) {
            if (data[i] == data[mindex]) c++;
        }

        System.out.println(c > N / 2 ? data[mindex] : -1);
    }

    @Test
    public void leaders() {
        int N = 6;
        int[] data = {16, 17, 4, 3, 5, 2};

        System.out.print(data[N - 1]);

        int leader = data[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            if (data[i] > data[i + 1] && data[i] > leader) {
                System.out.print(" " + data[i]);
                leader = data[i];
            }
        }
        System.out.println("");
    }

    @Test
    public void equilibrium() {
        int i, n = 30;
        String br = "20 17 42 25 32 32 30 32 37 9 2 33 31 17 14 40 9 12 36 21 8 33 6 6 10 37 12 26 21 3";
        String s[] = br.split(" ");

        int rsum = 0;
        int lsum = 0;
        int data[] = new int[n];
        for (i = 0; i < n; i++) {
            data[i] = Integer.parseInt(s[i]);
            rsum = rsum + data[i];
        }

        lsum = data[0];
        for (i = 1; i < n; i++) {
            if (lsum == rsum) break;
            rsum = rsum - data[i - 1];
            lsum = lsum + data[i];
        }

        if (lsum == rsum) {
            System.out.println(i);
        } else {
            System.out.println(-1);
        }
    }

    @Test
    public void sort_0_1_2() {
        int s, m, e, temp, i, n = 12;
        String br = "1 0 2 1 2 0 1 1 0 0 1 2";
        String input[] = br.split(" ");

        s = 0;
        e = n - 1;
        int data[] = new int[n];
        for (i = 0; i < n; i++) {
            temp = Integer.parseInt(input[i]);
            if (temp == 2) {
                data[e--] = 2;
            } else {
                data[s++] = temp;
            }
        }
        s = 0;
        while (s < e) {
            if (data[s] == 0) s++;
            else if (data[e] == 1) e--;
            else {
                temp = data[s];
                data[s] = data[e];
                data[e] = temp;
                s++;
                e--;
            }
        }

        Arrays.stream(data).forEach(p -> {
            System.out.print(" " + p);
        });
    }

    @Test
    public void inversion() {
        int i, j, n = 42, count = 0, temp;
        String br = "468 335 1 170 225 479 359 463 465 206 146 282 328 462 492 496 443 328 437 392 105 403 154 293 383 422 217 219 396 448 227 272 39 370 413 168 300 36 395 204 312 323";
        String input[] = br.split(" ");

        int data[] = new int[n];
        data[0] = Integer.parseInt(input[0]);
        //temp = data[0];
        for (i = 1; i < n; i++) {
            data[i] = Integer.parseInt(input[i]);
            //  if(temp > data[i]) count++;
        }

        count = mergeSort(data, 0, n - 1);

        Arrays.stream(data).forEach(p -> {
            System.out.print(" " + p);
        });

        System.out.println("\n" + count);
    }

    private static int mergeSort(int[] data, int start, int end) {
        int count = 0;
        if (start < end) {
            int mid = (start + end) / 2;
            count = mergeSort(data, start, mid);
            count += mergeSort(data, mid + 1, end);
            count += merge(data, start, mid, end);
        }
        return count;
    }

    private static int merge(int[] data, int start, int mid, int end) {
        int i = start, j = mid + 1, k = 0, count = 0;

        int temp[] = new int[end - start + 1];

        while (i <= mid && j <= end) {
            if (data[i] <= data[j]) {
                temp[k++] = data[i++];
            } else {
                temp[k++] = data[j++];
                count += mid - i + 1;
            }
        }

        while (i <= mid) {
            temp[k++] = data[i++];
        }

        while (j <= end) {
            temp[k++] = data[j++];
        }

        for (i = start, k = 0; i <= end; i++) {
            data[i] = temp[k++];
        }

        return count;
    }

    @Test
    public void nextBig() {
        int i, j, n = 4, next, temp;
        String br = "1 3 2 4";
        String input[] = br.split(" ");

        StringBuilder sb = new StringBuilder();

        int data[] = new int[n];
        for (i = 0; i < n; i++) {
            data[i] = Integer.parseInt(input[i]);
        }

        int ans[] = new int[n];

        Stack s = new Stack();

        for (i = n - 1; i >= 0; i--) {
            while (!s.isEmpty() && (int) s.peek() < data[i]) {
                s.pop();
            }
            ans[i] = s.isEmpty() ? -1 : (int) s.peek();
            s.push(data[i]);
        }

        Arrays.stream(ans).forEach(p -> {
            System.out.print(" " + p);
        });
    }

    @Test
    public void nonRepeatingChar() {
        int i, j, n = 284;
        String br = "w l r b b m q b h c d a r z o w k k y h i d d q s c d x r j m o w f r x s j y b l d b e f s a r c b y n e c d y g g x x p k l o r e l l n m p a p q f w k h o p k m c o q h n w n k u e w h s q m g b b u q c l j j i v s w m d k q t b x i x m v t r r b l j p t n s n f w z q f j m a f a d r r w s o f s b c n u v q h f f b s a q x w p q c a c e h c h z v f r k m l n o z j k p q p x r j x k i t z y x a c b h h k i c q c o e n d t o m f g d w d w f c g p x i q v k u y t d l c g d e w h t a c i o h o r d t q k v w c s g s p q o q m s b o a g u w n n y q x n z l g d g w";
        String input[] = br.split(" ");

        int flag[] = new int[26];
        for (i = 0; i < 26; i++) {
            flag[i] = 0;
        }

        char data[] = new char[n];
        int non = 0;
        data[0] = input[0].charAt(0);
        flag[(int) data[0] - 97]++;

        StringBuilder sb = new StringBuilder();
        sb.append(data[0]);
        for (i = 1; i < n; i++) {
            data[i] = input[i].charAt(0);
            flag[(int) data[i] - 97]++;

            while (non <= i && flag[(int) data[non] - 97] > 1) {
                non++;
            }
            if (non > i) {
                sb.append(" -1");
            } else {
                sb.append(" " + data[non]);
            }
        }
        System.out.println(sb);
    }

    @Test
    public void anagram() {
        String br = "geeksforgeeks forgeeksgeeks";
        String input[] = br.split(" ");
        int i;
        if (input[0].length() != input[1].length()) {
            System.out.println("NO");
        } else {
            int freq[] = new int[26];
            for (i = 0; i < 26; i++) {
                freq[i] = 0;
            }

            input[0].chars().forEach(p -> {
                freq[p - 97]++;
            });

            input[1].chars().forEach(p -> {
                freq[p - 97]--;
            });

            for (i = 0; i < 26; i++) {
                if (freq[i] != 0) {
                    System.out.println("NO");
                    break;
                }
            }
            if (i == 26) {
                System.out.println("YES");
            }
        }
    }

    @Test
    public void lcs() {
        int n, m, max;
        String br = "ABC ACDE";
        String input[] = br.split(" ");

        n = input[0].length();
        m = input[1].length();

        int lcs[][] = new int[n][m];

        int i, j;
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                lcs[i][j] = 0;
            }
        }

        max = 0;
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                if (input[0].charAt(i) == input[1].charAt(j)) {

                    if (i == 0 || j == 0) {
                        lcs[i][j] = 1;
                    } else {
                        lcs[i][j] = lcs[i - 1][j - 1] + 1;
                    }

                    if (max < lcs[i][j]) {
                        max = lcs[i][j];
                    }
                }
            }
        }
        System.out.println(max);

        Stream.of(lcs).map(Arrays::toString).forEach(System.out::println);
    }

    @Test
    public void reverseEachCharOfWord() {
        String br = "i.like.this.program.very.much";

        String ans = Arrays.stream(br.split("\\.")).map(String::toCharArray).map(r -> {
            int i = 0, j = r.length - 1;
            while (i <= j) {
                char t = r[i];
                r[i++] = r[j];
                r[j--] = t;
            }
            return String.valueOf(r);
        }).collect(Collectors.joining(""));

        System.out.println(br);
        System.out.println(ans);
    }

    @Test
    public void reverseEachWord() {
        String br = "i.like.this.program.very.much";

        String[] words = br.split("\\.");
        int i = 0, j = words.length - 1;
        while (i <= j) {
            String w = words[i];
            words[i++] = words[j];
            words[j--] = w;
        }

        String ans = Arrays.stream(words).collect(Collectors.joining(""));

        System.out.println(br);
        System.out.println(ans);
    }

    @Test
    public void binaryOddEvenReverse() throws Exception {
        String binaryString = Integer.toBinaryString(23);
        String leadZero = "00000000";

        binaryString = (leadZero + binaryString).substring(binaryString.length());

        char[] chars = binaryString.toCharArray();

        System.out.println(Arrays.toString(chars));
        int i = 0;
        while (i < 8) {
            char temp = chars[i];
            chars[i] = chars[i + 1];
            chars[i + 1] = temp;
            i += 2;
        }

        System.out.println(Arrays.toString(chars));
        System.out.println(Integer.parseInt(String.valueOf(chars), 2));
    }

    @Test
    public void removeDuplicate() {
        String input = "geeksforgeeks";
        boolean exists[] = new boolean[256];
        String ans = input.chars().filter(x -> {
            if (!exists[x]) {
                exists[x] = true;
                return true;
            }
            return false;
        }).mapToObj(x -> (char) x).map(String::valueOf).collect(Collectors.joining());

        System.out.println(ans);
    }

    @Test
    public void romanToInt() {
        String input = "MMMDCCXXIV";
        Map<Character, Integer> decode = new HashMap<>();
        decode.put('I', 1);
        decode.put('V', 5);
        decode.put('X', 10);
        decode.put('L', 50);
        decode.put('C', 100);
        decode.put('D', 500);
        decode.put('M', 1000);


        int i = input.length() - 1, val = 0;
        char[] chars = input.toCharArray();
        Character prev = null;

        while (i >= 0) {
            char curr = chars[i--];
            if (prev == null) {
                val += decode.get(curr);
            } else if ((curr == 'I' && prev == 'V' || curr == 'I' && prev == 'X') || (curr == 'X' && prev == 'L' || curr == 'X' && prev == 'C') || (curr == 'C' && prev == 'D' || curr == 'C' && prev == 'M')) {
                val -= decode.get(curr);
            } else {
                val += decode.get(curr);
            }

            if (curr == 'V' || curr == 'X' || curr == 'L' || curr == 'C' || curr == 'M' || curr == 'D') {
                prev = curr;
            } else {
                prev = null;
            }
        }


        System.out.println(val);


    }

    @Test
    public void commonPrefix() {
        int i, k, num = 4;

        String input[] = "d d d d".split(" ");

        int sml = 0;
        for (i = 1; i < num; i++) {
            if (input[i].length() < input[sml].length()) {
                sml = i;
            }
        }

        for (k = 0; k < input[sml].length(); k++) {
            char check = input[sml].charAt(k);

            for (i = 0; i < num; i++) {
                if (input[i].charAt(k) != check) {
                    break;
                }
            }

            if (i != num) {
                break;
            }
        }

        if (k == 0) {
            System.out.println("-1");
        } else {
            System.out.println(input[sml].substring(0, k));
        }
    }

    @Test
    public void commonUniqueElement() {

        List<String> a1 = Arrays.asList("10 37 63 72 85 91 96 108 132 164 173 180 191 200 259 283 306 321 337 341 349 377 380 413 417 419 445 446 453 466 467 469 523 543 551 601 625 627 638 660 661 669 685 709 737 757 774 814 882 888 895 900 900 925 927 931 934 937 960 974 982 997".split(" "));
        Set<String> a2 = new HashSet<>(Arrays.asList("82 85 128 151 207 211 225 229 256 270 302 322 343 373 397 423 588 600 631 659 668 706 714 722 812 820 905 921 940 941 985".split(" ")));
        Set<String> a3 = new HashSet<>(Arrays.asList("5 22 37 43 47 134 143 156 190 191 203 203 206 210 223 249 250 256 260 274 293 299 300 304 304 327 334 337 369 380 386 499 501 505 507 525 529 530 568 568 580 582 591 614 622 626 641 649 673 714 733 747 754 755 755 770 777 785 789 798 809 820 843 851 851 861 869 873 885 891 899 909 945 959 962 973 991 994 995".split(" ")));
        int n = a1.size();

        Set<String> a4 = new LinkedHashSet<>();

        for (int i = 0; i < n; i++) {
            if (a2.contains(a1.get(i)) && a3.contains(a1.get(i)) && !a4.contains(a1.get(i))) {
                a4.add(a1.get(i));
            }
        }

        if (a4.isEmpty()) {
            System.out.println(-1);
        } else {
            System.out.println(a4.stream().collect(Collectors.joining(" ")));
        }
    }

    @Test
    public void pattern101() {
        boolean start = false, mid = false, end = false;

        String s = "1010101010";
        int count = 0;
        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (c != '1' && c != '0') {
                start = false;
                mid = false;
            }

            if (c == '1') {
                if (start && mid) {
                    count++;
                } else {
                    start = true;
                    mid = false;
                }
            }

            if (c == '0' && start) {
                mid = true;
            } else {
                mid = false;
            }
        }
        System.out.println(count);
    }

    void testMethodArgs(String... args) {
        Arrays.stream(args).forEach(p -> {
            System.out.print(" " + p);
        });
    }


    @Test
    public void replaceAllTest() {

        String text = UUID.randomUUID().toString();
        System.out.println(text);
        String val1 = text.replace("-", "");
        String val2 = text.replaceAll("-", "");

        Assert.assertEquals(val1, val2);
    }

    @Test
    public void stringEquals() {
        String a = new String("ABC");
        String b = new String("ABC");

        String c = "ABC";
        String d = "ABC";


        Assert.assertFalse(a == b);
        Assert.assertTrue(c == d);

        Assert.assertTrue(a.equals(b));
    }

    private static final String FROM_ADDR = "merge@amdocs.com";
    private static final String CC_ADDR = "merge@amdocs.com";
    private static final String MAIL_HOST = "cyplimdagfe1.corp.amdocs.com";

    /* @Test
     @Ignore
     public void mailMe() throws Exception {
         sendMail("Dummy Body", "Test", "ajesingh@amdocs.com");
     }

     public void sendMail(final String mailBody, final String mailSubject, final String toRecipient) throws Exception {
         Properties props = new Properties();
         props.setProperty("mail.transport.protocol", "smtp");
         props.setProperty("mail.host", MAIL_HOST);

         final Session mailSession = Session.getDefaultInstance(props, null);
         mailSession.setDebug(true);
         final Transport transport = mailSession.getTransport();

         final MimeMessage message = new MimeMessage(mailSession);

         message.setSubject(mailSubject);
         message.setFrom(new InternetAddress(FROM_ADDR));
         String[] toList = toRecipient.split(",");
         Address[] address = new InternetAddress[toList.length];
         for (int i = 0; i < toList.length; ++i) {
             address[i] = new InternetAddress(toList[i]);
         }
         message.addRecipients(Message.RecipientType.TO, address);
         message.addRecipient(Message.RecipientType.CC, new InternetAddress(CC_ADDR));

         // This HTML mail have to 2 primary parts, the BODY and the embedded image
         final MimeMultipart multipart = new MimeMultipart("related");

         // first part (the html)
         final BodyPart messageBodyPart = new MimeBodyPart();
         messageBodyPart.setContent(mailBody, "text/html");
         multipart.addBodyPart(messageBodyPart);

         // final Map<String, String> imagesMap = createImagesMap() ;
         // embedImages( multipart, imagesMap ) ;

         message.setContent(multipart);

         transport.connect();

         final Address[] addressArray = message.getRecipients(Message.RecipientType.TO);
         final Address[] toSendAddressArray = new Address[addressArray.length + 1];

         for (int i = 0; i < toSendAddressArray.length - 1; i++) {
             toSendAddressArray[i] = addressArray[i];
         }

         toSendAddressArray[toSendAddressArray.length - 1] = new InternetAddress(CC_ADDR);

         transport.sendMessage(message, toSendAddressArray);

         transport.close();
     }
 */
    class Balloon {
        String color;

        public Balloon(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }
    }

    @Test
    public void passByValue() {
        Balloon r = new Balloon("Red");
        Balloon b = new Balloon("Blue");

        swapBalloon(r, b);

        System.out.println(r.getColor());
        System.out.println(b.getColor());

    }

    private void swapBalloon(Balloon r, Balloon b) {
        Balloon t = r;
        r = b;
        b = t;
    }


    @Test
    public void checkRegexHidden() {
        String[] files = {
            ".afile",
            ".anotherfile",
            "bfile.file",
            "bnotherfile.file",
            ".afolder/",
            ".anotherfolder/",
            "bfolder/",
            "bnotherfolder/",
            "",
        };
        for (String file : files) {
            System.out.printf("%-18s %6b%6b%n", file,
                file.matches("^(?!\\.).+$"),
                !file.startsWith("")
            );
        }
    }

    @Test
    public void findAllpermutation() {
        String val = "ABCD";
        permute(val, 0, val.length() - 1);
    }

    private void permute(String val, int l, int r) {
        if (l == r) {
            System.out.println(val);
        } else {
            for (int i = l; i <= r; i++) {
                val = swap(val, l, i);
                permute(val, l + 1, r);
                val = swap(val, l, i);
            }
        }
    }

    private String swap(String val, int l, int i) {
        char[] chars = val.toCharArray();
        char temp = chars[l];
        chars[l] = chars[i];
        chars[i] = temp;
        return String.valueOf(chars);
    }
}

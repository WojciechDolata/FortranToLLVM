      implicit none

      integer a, b

      a = 2
      b = 3
      a = 5
      a = 4
      b = 2
      a = a + b + a * b
      write(*, '(a)') a

      end
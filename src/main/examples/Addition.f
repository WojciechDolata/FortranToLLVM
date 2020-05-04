      implicit none

      integer a, b
      logical d, e
      e = .true.
      a = 2
      b = 3
      a = a + b * a
      d = a .eqv. b
      write(*, '(a)') a

      end
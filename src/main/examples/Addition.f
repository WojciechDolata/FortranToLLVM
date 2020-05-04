      implicit none

      logical d, e, f
      integer a, b
      a = 3
      b = 4
      e = .true.
      f = .false.

      d = .not. (e .and. f .and. a .gt. b)

      end